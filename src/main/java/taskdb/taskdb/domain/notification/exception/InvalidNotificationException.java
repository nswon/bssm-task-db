package taskdb.taskdb.domain.notification.exception;

import taskdb.taskdb.global.exception.InternalException;

public class InvalidNotificationException extends InternalException {
    private static final String MESSAGE = "잘못된 알림의 종류입니다.";

    public InvalidNotificationException() {
        super(MESSAGE);
    }
}
