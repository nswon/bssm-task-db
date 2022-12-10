package taskdb.taskdb.domain.store.exception;

import taskdb.taskdb.infrastructure.exception.NotFoundException;

public class StoreQuestionNotFoundException extends NotFoundException {
    private static final String MESSAGE = "저장한 질문글이 존재하지 않습니다.";

    public StoreQuestionNotFoundException() {
        super(MESSAGE);
    }
}
