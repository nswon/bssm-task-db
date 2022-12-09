package taskdb.taskdb.adapter.answer.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import taskdb.taskdb.domain.answer.domain.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
