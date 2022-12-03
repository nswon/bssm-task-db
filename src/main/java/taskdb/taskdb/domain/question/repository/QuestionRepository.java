package taskdb.taskdb.domain.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taskdb.taskdb.domain.question.domain.Category;
import taskdb.taskdb.domain.question.domain.Question;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByCategory(Category category);
}
