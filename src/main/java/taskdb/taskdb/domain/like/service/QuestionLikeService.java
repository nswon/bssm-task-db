package taskdb.taskdb.domain.like.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.like.domain.QuestionLike;
import taskdb.taskdb.domain.like.port.QuestionLikeReader;
import taskdb.taskdb.domain.like.port.QuestionLikeStore;
import taskdb.taskdb.domain.question.domain.Question;
import taskdb.taskdb.domain.question.port.QuestionReader;
import taskdb.taskdb.domain.question.repository.QuestionRepository;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.port.UserReader;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionLikeService {
    private static final String FOUR_A_M_CORN = "0 0 4 * * *";
    private final QuestionRepository questionRepository;
    private final UserReader userReader;
    private final QuestionReader questionReader;
    private final QuestionLikeReader questionLikeReader;
    private final QuestionLikeStore questionLikeStore;

    public boolean like(Long id) {
        User user = userReader.getCurrentUser();
        Question question = questionReader.getQuestionById(id);

        boolean hasLike = questionLikeReader.hasByQuestionAndUser(question, user);
        if(hasLike) {
            questionLikeStore.delete(question, user);
            question.downLikeCount();
            return true;
        }
        QuestionLike questionLike = QuestionLike.builder()
                .question(question)
                .user(user)
                .build();

        questionLike.addQuestion();
        questionLike.addUser();
        questionLikeStore.store(questionLike);
        question.addLikeCount();
        return true;
    }

    @Scheduled(cron = FOUR_A_M_CORN)
    public void syncLike() {
        questionRepository.findAll()
                .forEach(this::syncQuestionLike);
    }

    private void syncQuestionLike(Question question) {
        int likeCount = questionLikeReader.getQuestionLikeByQuestion(question).size();
        question.syncLikeCount(likeCount);
    }
}
