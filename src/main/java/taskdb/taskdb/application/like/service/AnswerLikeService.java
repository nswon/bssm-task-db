package taskdb.taskdb.application.like.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.application.answer.port.out.GetAnswerPort;
import taskdb.taskdb.application.like.port.in.AnswerLikeUseCase;
import taskdb.taskdb.application.like.port.out.DeleteAnswerLikePort;
import taskdb.taskdb.application.like.port.out.ExistAnswerLikePort;
import taskdb.taskdb.application.like.port.out.GetAnswerLikePort;
import taskdb.taskdb.application.like.port.out.SaveAnswerLikePort;
import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.like.entity.AnswerLike;
import taskdb.taskdb.domain.user.entity.User;
import taskdb.taskdb.application.user.port.out.GetUserPort;
import taskdb.taskdb.application.like.dto.AnswerLikeMapper;

@Service
@RequiredArgsConstructor
@Transactional
public class AnswerLikeService implements AnswerLikeUseCase {
    private static final String FOUR_A_M_CORN = "0 0 4 * * *";
    private final GetUserPort getUserPort;
    private final ExistAnswerLikePort existAnswerLikePort;
    private final DeleteAnswerLikePort deleteAnswerLikePort;
    private final SaveAnswerLikePort saveAnswerLikePort;
    private final GetAnswerLikePort getAnswerLikePort;
    private final AnswerLikeMapper answerLikeMapper;
    private final GetAnswerPort getAnswerPort;

    @Override
    public boolean like(Long id) {
        User user = getUserPort.getCurrentUser();
        Answer answer = getAnswerPort.getAnswer(id);
        boolean hasLike = existAnswerLikePort.hasAnswerLike(answer, user);
        if(hasLike) {
            deleteAnswerLikePort.delete(answer, user);
            answer.downLikeCount();
            return true;
        }

        AnswerLike answerLike = answerLikeMapper.of(answer, user);
        saveAnswerLikePort.save(answerLike);
        answer.addLikeCount();
        return true;
    }

    @Scheduled(cron = FOUR_A_M_CORN)
    public void syncLike() {
        getAnswerPort.getAnswers()
                .forEach(this::syncAnswerLike);
    }

    private void syncAnswerLike(Answer answer) {
        int likeCount = getAnswerLikePort.getAnswerLike(answer).size();
        answer.syncLikeCount(likeCount);
    }
}
