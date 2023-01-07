package taskdb.taskdb.application.comment.port.out;

import taskdb.taskdb.domain.comment.entity.Comment;
import taskdb.taskdb.domain.question.entity.Question;

import java.util.List;
import java.util.UUID;

public interface GetCommentPort {
    Comment getComment(UUID id);
    List<Comment> getComments(Question question);
}
