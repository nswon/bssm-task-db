package taskdb.taskdb.adapter.like.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import taskdb.taskdb.domain.like.entity.QuestionLike;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.user.entity.User;

import java.util.List;

public interface QuestionLikeRepository extends JpaRepository<QuestionLike, Long> {
    boolean existsByQuestionAndUser(Question question, User user);
    void deleteByQuestionAndUser(Question question, User user);
    List<QuestionLike> findByQuestion(Question question);
}
