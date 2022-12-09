package taskdb.taskdb.adapter.question.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import taskdb.taskdb.domain.question.entity.Category;
import taskdb.taskdb.domain.question.entity.Question;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByCategory(Category category);
}
