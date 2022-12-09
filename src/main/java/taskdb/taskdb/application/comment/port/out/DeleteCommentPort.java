package taskdb.taskdb.application.comment.port.out;

import taskdb.taskdb.domain.comment.domain.Comment;

public interface DeleteCommentPort {
    Comment delete(Comment comment);
}
