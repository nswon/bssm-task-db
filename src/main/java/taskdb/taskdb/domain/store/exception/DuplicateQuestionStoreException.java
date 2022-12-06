package taskdb.taskdb.domain.store.exception;

import taskdb.taskdb.global.exception.BadRequestException;

public class DuplicateQuestionStoreException extends BadRequestException {
    private static final String MESSAGE = "이미 저장한 질문글입니다.";

    public DuplicateQuestionStoreException() {
        super(MESSAGE);
    }
}
