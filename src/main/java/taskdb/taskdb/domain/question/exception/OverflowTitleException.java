package taskdb.taskdb.domain.question.exception;

import taskdb.taskdb.global.exception.BadRequestException;

public class OverflowTitleException extends BadRequestException {
    private static final String MESSAGE = "제목의 범위가 초과되었습니다.";

    public OverflowTitleException() {
        super(MESSAGE);
    }
}
