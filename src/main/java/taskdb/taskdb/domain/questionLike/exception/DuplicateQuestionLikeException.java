package taskdb.taskdb.domain.questionLike.exception;

import taskdb.taskdb.infrastructure.exception.BadRequestException;

public class DuplicateQuestionLikeException extends BadRequestException {
    private static final String MESSAGE = "이미 질문글 좋아요를 누른 상태입니다.";

    public DuplicateQuestionLikeException() {
        super(MESSAGE);
    }
}
