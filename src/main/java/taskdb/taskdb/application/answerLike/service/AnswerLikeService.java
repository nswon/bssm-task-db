package taskdb.taskdb.application.answerLike.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.application.answer.port.out.GetAnswerPort;
import taskdb.taskdb.application.answerLike.port.in.AnswerLikeUseCase;
import taskdb.taskdb.application.answerLike.port.out.ExistAnswerLikePort;
import taskdb.taskdb.application.answerLike.port.out.ExistAnswerUnLikePort;
import taskdb.taskdb.application.answerLike.port.out.SaveAnswerLikePort;
import taskdb.taskdb.application.answerLike.port.out.DeleteAnswerUnLikePort;
import taskdb.taskdb.domain.answer.entity.Answer;
import taskdb.taskdb.domain.answerLike.entity.AnswerLike;
import taskdb.taskdb.domain.answerLike.exception.DuplicateAnswerLikeException;
import taskdb.taskdb.domain.user.entity.User;
import taskdb.taskdb.application.user.port.out.GetUserPort;
import taskdb.taskdb.application.answerLike.dto.AnswerLikeMapper;

@Service
@RequiredArgsConstructor
@Transactional
public class AnswerLikeService implements AnswerLikeUseCase {
    private final GetUserPort getUserPort;
    private final ExistAnswerLikePort existAnswerLikePort;
    private final DeleteAnswerUnLikePort deleteAnswerUnLikePort;
    private final SaveAnswerLikePort saveAnswerLikePort;
    private final AnswerLikeMapper answerLikeMapper;
    private final GetAnswerPort getAnswerPort;
    private final ExistAnswerUnLikePort existAnswerUnLikePort;

    @Override
    public void like(Long id) {
        User user = getUserPort.getCurrentUser();
        Answer answer = getAnswerPort.getAnswer(id);
        checkLike(answer, user);
        AnswerLike answerLike = answerLikeMapper.of(answer, user);
        saveAnswerLikePort.save(answerLike);
        checkLikeCount(answer, user);
        answer.addLikeCount();
    }

    private void checkLike(Answer answer, User user) {
        if(existAnswerLikePort.hasAnswerLike(answer, user)) {
            throw new DuplicateAnswerLikeException();
        }
    }

    private void checkLikeCount(Answer answer, User user) {
        if(existAnswerUnLikePort.hasAnswerUnLike(answer, user)) {
            answer.addLikeCount();
            deleteAnswerUnLikePort.delete(answer, user);
        }
    }
}
