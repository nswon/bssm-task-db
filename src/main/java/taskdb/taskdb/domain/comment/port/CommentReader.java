package taskdb.taskdb.domain.comment.port;

import taskdb.taskdb.domain.comment.domain.Comment;

public interface CommentReader {
    Comment getCommentById(Long id);
}
