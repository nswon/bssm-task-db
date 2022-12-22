package taskdb.taskdb.application.answerLike.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.application.answer.port.out.GetAnswerPort;
import taskdb.taskdb.application.answerLike.port.out.GetAnswerLikePort;
import taskdb.taskdb.application.answerLike.port.out.GetAnswerUnLikePort;
import taskdb.taskdb.domain.answer.domain.Answer;

@Service
@RequiredArgsConstructor
@Transactional
public class AnswerLikeScheduledService {
    private static final String FOUR_A_M_CORN = "0 0 4 * * *";
    private final GetAnswerLikePort getAnswerLikePort;
    private final GetAnswerUnLikePort getAnswerUnLikePort;
    private final GetAnswerPort getAnswerPort;

    @Scheduled(cron = FOUR_A_M_CORN)
    public void syncLike() {
        getAnswerPort.getAnswers()
                .forEach(this::syncAnswerLike);
    }

    private void syncAnswerLike(Answer answer) {
        int likeCount = getAnswerLikePort.getAnswerLike(answer).size();
        int unlikeCount = getAnswerUnLikePort.getAnswerUnLikes(answer).size();
        answer.syncLikeCount(likeCount - unlikeCount);
    }
}
