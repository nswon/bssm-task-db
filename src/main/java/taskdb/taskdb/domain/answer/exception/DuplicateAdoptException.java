package taskdb.taskdb.domain.answer.exception;

import taskdb.taskdb.infrastructure.exception.BadRequestException;

public class DuplicateAdoptException extends BadRequestException {
    private static final String MESSAGE = "이미 다른 답변이 채택된 상태입니다.";

    public DuplicateAdoptException() {
        super(MESSAGE);
    }
}
