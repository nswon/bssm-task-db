package taskdb.taskdb.domain.answer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taskdb.taskdb.domain.answer.domain.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
