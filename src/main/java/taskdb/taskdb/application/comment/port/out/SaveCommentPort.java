package taskdb.taskdb.application.comment.port.out;

import taskdb.taskdb.domain.comment.entity.Comment;

public interface SaveCommentPort {
    Comment save(Comment comment);
}
