package taskdb.taskdb.application.comment.port.out;

import taskdb.taskdb.domain.comment.entity.Comment;
import taskdb.taskdb.domain.question.entity.Question;

import java.util.List;

public interface GetCommentPort {
    Comment getComment(Long id);
    List<Comment> getComments(Question question);
}
