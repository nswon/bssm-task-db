package taskdb.taskdb.domain.answer.exception;

import taskdb.taskdb.global.exception.NotFoundException;

public class AnswerNotFoundException extends NotFoundException {
    private static final String MESSAGE = "답변이 존재하지 않습니다.";

    public AnswerNotFoundException() {
        super(MESSAGE);
    }
}
