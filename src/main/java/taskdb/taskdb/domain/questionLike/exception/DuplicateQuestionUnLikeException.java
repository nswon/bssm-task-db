package taskdb.taskdb.domain.questionLike.exception;

import taskdb.taskdb.infrastructure.exception.BadRequestException;

public class DuplicateQuestionUnLikeException extends BadRequestException {
    private static final String MESSAGE = "이미 질문글 싫어요를 누른 상태입니다.";

    public DuplicateQuestionUnLikeException() {
        super(MESSAGE);
    }
}
