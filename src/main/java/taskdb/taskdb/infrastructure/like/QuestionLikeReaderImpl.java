package taskdb.taskdb.infrastructure.like;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.like.domain.QuestionLike;
import taskdb.taskdb.domain.like.port.QuestionLikeReader;
import taskdb.taskdb.domain.like.repository.QuestionLikeRepository;
import taskdb.taskdb.domain.question.domain.Question;
import taskdb.taskdb.domain.user.domain.User;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionLikeReaderImpl implements QuestionLikeReader {
    private final QuestionLikeRepository questionLikeRepository;

    @Override
    public boolean hasByQuestionAndUser(Question question, User user) {
        return questionLikeRepository.existsByQuestionAndUser(question, user);
    }

    @Override
    public List<QuestionLike> getQuestionLikeByQuestion(Question question) {
        return questionLikeRepository.findByQuestion(question);
    }
}
