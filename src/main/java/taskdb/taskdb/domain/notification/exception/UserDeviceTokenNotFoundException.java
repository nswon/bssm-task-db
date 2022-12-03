package taskdb.taskdb.domain.notification.exception;

import taskdb.taskdb.global.exception.NotFoundException;

public class UserDeviceTokenNotFoundException extends NotFoundException {
    private static final String MESSAGE = "사용자 기기 토큰이 존재하지 않습니다.";

    public UserDeviceTokenNotFoundException() {
        super(MESSAGE);
    }
}
