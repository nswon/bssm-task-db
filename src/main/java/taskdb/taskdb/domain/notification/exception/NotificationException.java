package taskdb.taskdb.domain.notification.exception;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import taskdb.taskdb.global.exception.BaseException;
import taskdb.taskdb.global.exception.BaseExceptionType;

@AllArgsConstructor
@RequiredArgsConstructor
public class NotificationException extends BaseException {
    private BaseExceptionType exceptionType;

    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
