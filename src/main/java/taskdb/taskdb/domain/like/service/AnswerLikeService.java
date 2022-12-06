package taskdb.taskdb.domain.like.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.answer.port.AnswerReader;
import taskdb.taskdb.domain.answer.repository.AnswerRepository;
import taskdb.taskdb.domain.like.domain.AnswerLike;
import taskdb.taskdb.domain.like.port.AnswerLikeReader;
import taskdb.taskdb.domain.like.port.AnswerLikeStore;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.port.UserReader;
import taskdb.taskdb.mapper.AnswerLikeMapper;

@Service
@RequiredArgsConstructor
@Transactional
public class AnswerLikeService {
    private static final String FOUR_A_M_CORN = "0 0 4 * * *";
    private final AnswerRepository answerRepository;
    private final UserReader userReader;
    private final AnswerLikeReader answerLikeReader;
    private final AnswerLikeStore answerLikeStore;
    private final AnswerReader answerReader;
    private final AnswerLikeMapper answerLikeMapper;

    public boolean like(Long id) {
        User user = userReader.getCurrentUser();
        Answer answer = answerReader.getAnswerById(id);
        boolean hasLike = answerLikeReader.hasByAnswerAndUser(answer, user);
        if(hasLike) {
            answerLikeStore.delete(answer, user);
            answer.downLikeCount();
            return true;
        }

        AnswerLike answerLike = answerLikeMapper.of(answer, user);
        answerLikeStore.store(answerLike);
        answer.addLikeCount();
        return true;
    }

    @Scheduled(cron = FOUR_A_M_CORN)
    public void syncLike() {
        answerRepository.findAll()
                .forEach(this::syncAnswerLike);
    }

    private void syncAnswerLike(Answer answer) {
        int likeCount = answerLikeReader.getAnswerLikesByAnswer(answer).size();
        answer.syncLikeCount(likeCount);
    }
}
