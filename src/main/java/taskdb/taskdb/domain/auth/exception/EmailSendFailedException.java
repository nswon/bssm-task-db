package taskdb.taskdb.domain.auth.exception;

import taskdb.taskdb.infrastructure.exception.InternalException;

public class EmailSendFailedException extends InternalException {
    private static final String MESSAGE = "이메일 전송에 실패하였습니다.";

    public EmailSendFailedException() {
        super(MESSAGE);
    }
}
