package taskdb.taskdb.adapter.answer.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import taskdb.taskdb.domain.answer.entity.Answer;
import taskdb.taskdb.domain.question.entity.Question;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestion(Question question);
}
