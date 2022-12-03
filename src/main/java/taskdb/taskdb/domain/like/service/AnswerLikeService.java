package taskdb.taskdb.domain.like.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.answer.repository.AnswerRepository;
import taskdb.taskdb.domain.answer.facade.AnswerFacade;
import taskdb.taskdb.domain.like.domain.AnswerLike;
import taskdb.taskdb.domain.like.repository.AnswerLikeRepository;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.facade.UserFacade;

@Service
@RequiredArgsConstructor
@Transactional
public class AnswerLikeService {
    private static final String FOUR_A_M_CORN = "0 0 4 * * *";
    private final AnswerLikeRepository answerLikeRepository;
    private final AnswerRepository answerRepository;
    private final UserFacade userFacade;
    private final AnswerFacade answerFacade;

    public boolean like(Long id) {
        User user = userFacade.getCurrentUser();
        Answer answer = answerFacade.getAnswerById(id);
        if(answerLikeRepository.existsByAnswerAndUser(answer, user)) {
            answerLikeRepository.deleteByAnswerAndUser(answer, user);
            answer.downLikeCount();
            return true;
        }

        AnswerLike answerLike = AnswerLike.builder()
                .answer(answer)
                .user(user)
                .build();
        answerLike.addAnswer();
        answerLike.addUser();
        answerLikeRepository.save(answerLike);
        answer.addLikeCount();
        return true;
    }

    @Scheduled(cron = FOUR_A_M_CORN)
    public void syncLike() {
        answerRepository.findAll()
                .forEach(this::syncAnswerLike);
    }

    private void syncAnswerLike(Answer answer) {
        int likeCount = answerLikeRepository.findByAnswer(answer).size();
        answer.syncLikeCount(likeCount);
    }
}
