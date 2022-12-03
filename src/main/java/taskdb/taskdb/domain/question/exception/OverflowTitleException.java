package taskdb.taskdb.domain.question.exception;

import taskdb.taskdb.global.exception.BadRequestException;

public class OverflowTitleException extends BadRequestException {
    private static final String MESSAGE = "제목의 범위를 초과하였습니다.";

    public OverflowTitleException() {
        super(MESSAGE);
    }
}
