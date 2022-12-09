package taskdb.taskdb.application.comment.port.out;

import taskdb.taskdb.domain.comment.domain.Comment;

public interface GetCommentPort {
    Comment getComment(Long id);
}
