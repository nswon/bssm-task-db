package taskdb.taskdb.adapter.answerLike.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import taskdb.taskdb.domain.answer.entity.Answer;
import taskdb.taskdb.domain.answerLike.entity.AnswerUnLike;
import taskdb.taskdb.domain.user.entity.User;

import java.util.List;

public interface AnswerUnLikeRepository extends JpaRepository<AnswerUnLike, Long> {
    boolean existsByAnswerAndUser(Answer answer, User user);
    void deleteByAnswerAndUser(Answer answer, User user);
    List<AnswerUnLike> findByAnswer(Answer answer);
}
