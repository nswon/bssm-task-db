package taskdb.taskdb.global.exception.application;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {
    private static final String BASE_EXCEPTION_ERROR_MESSAGE = "BaseException errorMessage(): {}";
    private static final String BASE_EXCEPTION_ERROR_CODE = "BaseException errorCode(): {}";
    private static final String WRONG_REQUEST_HTTP_METHOD = "잘못된 메서드 요청입니다.";
    private static final int BAD_HTTP_METHOD_HTTP_STATUS = 405;
    private static final int BAD_REQUEST_HTTP_STATUS = 400;
    private static final String START_ERROR_MESSAGE = "[";
    private static final String CONFIRM_ERROR_MESSAGE = "](은)는 ";
    @ExceptionHandler(BaseException.class)
    public ResponseEntity handleBaseException(BaseException exception) {
        log.error(BASE_EXCEPTION_ERROR_MESSAGE, exception.getExceptionType().getErrorMessage());
        log.error(BASE_EXCEPTION_ERROR_CODE, exception.getExceptionType().getErrorCode());

        return new ResponseEntity(ExceptionDto.builder()
                .errorCode(exception.getExceptionType().getErrorCode())
                .errorMessage(exception.getExceptionType().getErrorMessage())
                .build(), exception.getExceptionType().getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity hadleMemberException(Exception exception) {
        exception.printStackTrace();
        return new ResponseEntity(HttpStatus.OK);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        ExceptionDto dto = ExceptionDto.builder()
                .errorCode(BAD_HTTP_METHOD_HTTP_STATUS)
                .errorMessage(WRONG_REQUEST_HTTP_METHOD).build();
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(dto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity processValidationError(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            appendFieldError(builder, fieldError);
            break;
        }

        return new ResponseEntity(ExceptionDto.builder()
                .errorCode(BAD_REQUEST_HTTP_STATUS)
                .errorMessage(builder.toString())
                .build(), HttpStatus.BAD_REQUEST);
    }

    private void appendFieldError(StringBuilder builder, FieldError fieldError) {
        builder.append(START_ERROR_MESSAGE);
        builder.append(fieldError.getField());
        builder.append(CONFIRM_ERROR_MESSAGE);
        builder.append(fieldError.getDefaultMessage());
    }

    @Data
    static class ExceptionDto {
        private Integer errorCode;
        private String errorMessage;

        @Builder
        public ExceptionDto(Integer errorCode, String errorMessage) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }
    }
}
