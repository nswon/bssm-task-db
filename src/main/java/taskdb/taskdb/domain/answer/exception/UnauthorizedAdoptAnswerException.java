package taskdb.taskdb.domain.answer.exception;

import taskdb.taskdb.infrastructure.exception.BadRequestException;

public class UnauthorizedAdoptAnswerException extends BadRequestException {
    private static final String MESSAGE = "채택된 답변은 수정 또는 삭제가 불가능합니다.";

    public UnauthorizedAdoptAnswerException() {
        super(MESSAGE);
    }
}
