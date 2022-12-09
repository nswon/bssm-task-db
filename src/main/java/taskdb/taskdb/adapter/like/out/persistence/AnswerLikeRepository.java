package taskdb.taskdb.adapter.like.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.like.entity.AnswerLike;
import taskdb.taskdb.domain.user.entity.User;

import java.util.List;

public interface AnswerLikeRepository extends JpaRepository<AnswerLike, Long> {
    boolean existsByAnswerAndUser(Answer answer, User user);
    void deleteByAnswerAndUser(Answer answer, User user);
    List<AnswerLike> findByAnswer(Answer answer);
}
