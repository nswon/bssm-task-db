package taskdb.taskdb.adapter.comment.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import taskdb.taskdb.domain.comment.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
