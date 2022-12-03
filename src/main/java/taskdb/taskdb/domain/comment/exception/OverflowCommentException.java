package taskdb.taskdb.domain.comment.exception;

import taskdb.taskdb.global.exception.BadRequestException;

public class OverflowCommentException extends BadRequestException {
    private static final String MESSAGE = "댓글의 범위를 초과하였습니다.";

    public OverflowCommentException() {
        super(MESSAGE);
    }
}
