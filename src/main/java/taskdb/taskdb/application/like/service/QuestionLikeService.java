package taskdb.taskdb.application.like.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.application.like.port.in.QuestionLikeUseCase;
import taskdb.taskdb.application.like.port.out.DeleteQuestionLikePort;
import taskdb.taskdb.application.like.port.out.ExistQuestionLikePort;
import taskdb.taskdb.application.like.port.out.GetQuestionLikePort;
import taskdb.taskdb.application.like.port.out.SaveQuestionLikePort;
import taskdb.taskdb.application.question.port.out.GetQuestionPort;
import taskdb.taskdb.domain.like.entity.QuestionLike;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.user.entity.User;
import taskdb.taskdb.application.user.port.out.GetUserPort;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionLikeService implements QuestionLikeUseCase {
    private static final String FOUR_A_M_CORN = "0 0 4 * * *";
    private final GetUserPort getUserPort;
    private final GetQuestionPort getQuestionPort;
    private final ExistQuestionLikePort existQuestionLikePort;
    private final DeleteQuestionLikePort deleteQuestionLikePort;
    private final SaveQuestionLikePort saveQuestionLikePort;
    private final GetQuestionLikePort getQuestionLikePort;

    @Override
    public boolean like(Long id) {
        User user = getUserPort.getCurrentUser();
        Question question = getQuestionPort.getQuestion(id);

        boolean hasLike = existQuestionLikePort.hasQuestionLike(question, user);
        if(hasLike) {
            deleteQuestionLikePort.delete(question, user);
            question.downLikeCount();
            return true;
        }
        QuestionLike questionLike = QuestionLike.builder()
                .question(question)
                .user(user)
                .build();

        questionLike.addQuestion();
        questionLike.addUser();
        saveQuestionLikePort.save(questionLike);
        question.addLikeCount();
        return true;
    }

    @Scheduled(cron = FOUR_A_M_CORN)
    public void syncLike() {
        getQuestionPort.getQuestions()
                .forEach(this::syncQuestionLike);
    }

    private void syncQuestionLike(Question question) {
        int likeCount = getQuestionLikePort.getQuestionLike(question).size();
        question.syncLikeCount(likeCount);
    }
}
