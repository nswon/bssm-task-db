package taskdb.taskdb.adapter.comment.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import taskdb.taskdb.domain.comment.entity.Comment;
import taskdb.taskdb.domain.question.entity.Question;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByQuestion(Question question);
}
