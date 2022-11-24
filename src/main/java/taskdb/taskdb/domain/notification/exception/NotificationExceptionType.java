package taskdb.taskdb.domain.notification.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import taskdb.taskdb.global.exception.application.BaseExceptionType;

@Getter
@AllArgsConstructor
public enum NotificationExceptionType implements BaseExceptionType {
    NOT_FOUND_TOKEN(900,HttpStatus.BAD_REQUEST, "기기 토큰이 존재하지 않습니다.");

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
