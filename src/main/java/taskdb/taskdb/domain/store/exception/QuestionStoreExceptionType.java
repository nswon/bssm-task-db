package taskdb.taskdb.domain.store.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import taskdb.taskdb.global.exception.BaseExceptionType;

@Getter
@AllArgsConstructor
public enum QuestionStoreExceptionType implements BaseExceptionType {
    NOT_FOUND_STORE_QUESTION(200,HttpStatus.BAD_REQUEST, "저장한 질문글이 존재하지 않습니다.");

    private final int errorCode;
    private final HttpStatus httpStatus;
    private final String errorMessage;

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
