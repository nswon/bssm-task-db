package taskdb.taskdb.domain.question.exception;

import taskdb.taskdb.global.exception.BadRequestException;

public class OverflowContentException extends BadRequestException {
    private static final String MESSAGE = "내용의 범위를 초과하였습니다.";

    public OverflowContentException() {
        super(MESSAGE);
    }
}
