package taskdb.taskdb.domain.question.exception;

import taskdb.taskdb.global.exception.NotFoundException;

public class QuestionNotFoundException extends NotFoundException {
    private static final String MESSAGE = "질문글이 존재하지 않습니다.";

    public QuestionNotFoundException() {
        super(MESSAGE);
    }
}
