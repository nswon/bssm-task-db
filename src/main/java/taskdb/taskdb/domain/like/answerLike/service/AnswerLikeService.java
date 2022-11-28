package taskdb.taskdb.domain.like.answerLike.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.answer.facade.AnswerFacade;
import taskdb.taskdb.domain.like.answerLike.domain.AnswerLike;
import taskdb.taskdb.domain.like.answerLike.domain.AnswerLikeRepository;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.facade.UserFacade;

@Service
@RequiredArgsConstructor
@Transactional
public class AnswerLikeService {
    private final AnswerLikeRepository answerLikeRepository;
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
}
