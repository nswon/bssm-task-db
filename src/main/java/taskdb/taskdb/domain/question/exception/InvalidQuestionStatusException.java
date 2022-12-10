package taskdb.taskdb.domain.question.exception;

import taskdb.taskdb.infrastructure.exception.BadRequestException;

public class InvalidQuestionStatusException extends BadRequestException {
    private static final String MESSAGE = "올바른 질문글 상태를 입력해주세요.";

    public InvalidQuestionStatusException() {
        super(MESSAGE);
    }
}
