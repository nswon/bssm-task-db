package taskdb.taskdb.application.comment.port.out;

import taskdb.taskdb.domain.comment.entity.Comment;

public interface DeleteCommentPort {
    Comment delete(Comment comment);
}
