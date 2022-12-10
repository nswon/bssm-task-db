package taskdb.taskdb.domain.question.exception;

import taskdb.taskdb.infrastructure.exception.BadRequestException;

public class InvalidCategoryException extends BadRequestException {
    private static final String MESSAGE = "올바른 카테고리를 입력해주세요.";

    public InvalidCategoryException() {
        super(MESSAGE);
    }
}
