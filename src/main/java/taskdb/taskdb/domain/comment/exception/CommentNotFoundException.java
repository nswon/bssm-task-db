package taskdb.taskdb.domain.comment.exception;

import taskdb.taskdb.global.exception.NotFoundException;

public class CommentNotFoundException extends NotFoundException {
    private static final String MESSAGE = "댓글이 존재하지 않습니다.";

    public CommentNotFoundException() {
        super(MESSAGE);
    }
}
