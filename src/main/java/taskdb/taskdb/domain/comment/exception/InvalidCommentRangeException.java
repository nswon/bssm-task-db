package taskdb.taskdb.domain.comment.exception;

import taskdb.taskdb.global.exception.BadRequestException;

public class InvalidCommentRangeException extends BadRequestException {
    private static final String MESSAGE = "잘못되 댓글의 범위입니다.";

    public InvalidCommentRangeException() {
        super(MESSAGE);
    }
}
