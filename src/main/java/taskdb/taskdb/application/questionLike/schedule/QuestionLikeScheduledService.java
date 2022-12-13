package taskdb.taskdb.application.questionLike.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.application.question.port.out.GetQuestionPort;
import taskdb.taskdb.application.questionLike.port.out.GetQuestionLikePort;
import taskdb.taskdb.application.questionLike.port.out.GetQuestionUnLikePort;
import taskdb.taskdb.domain.question.entity.Question;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionLikeScheduledService {
    private static final String FOUR_A_M_CORN = "0 0 4 * * *";
    private final GetQuestionLikePort getQuestionLikePort;
    private final GetQuestionPort getQuestionPort;
    private final GetQuestionUnLikePort getQuestionUnLikePort;


    @Scheduled(cron = FOUR_A_M_CORN)
    public void syncLike() {
        getQuestionPort.getQuestions()
                .forEach(this::syncQuestionLike);
    }

    private void syncQuestionLike(Question question) {
        int likeCount = getQuestionLikePort.getQuestionLike(question).size();
        int unlikeCount = getQuestionUnLikePort.getQuestionUnLikes(question).size();
        question.syncLikeCount(likeCount - unlikeCount);
    }
}
