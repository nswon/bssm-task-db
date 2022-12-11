package taskdb.taskdb.domain.comment.exception;

import taskdb.taskdb.infrastructure.exception.BadRequestException;

public class InvalidCommentRangeException extends BadRequestException {
    private static final String MESSAGE = "댓글은 최소 1글자에서 5000글자까지여야 합니다.";

    public InvalidCommentRangeException() {
        super(MESSAGE);
    }
}
