package taskdb.taskdb.application.answerLike.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.application.answer.port.out.GetAnswerPort;
import taskdb.taskdb.application.answerLike.port.out.DeleteAnswerLikePort;
import taskdb.taskdb.application.answerLike.dto.AnswerUnLikeMapper;
import taskdb.taskdb.application.answerLike.port.in.AnswerUnLikeUseCase;
import taskdb.taskdb.application.answerLike.port.out.ExistAnswerLikePort;
import taskdb.taskdb.application.answerLike.port.out.ExistAnswerUnLikePort;
import taskdb.taskdb.application.answerLike.port.out.SaveAnswerUnLikePort;
import taskdb.taskdb.application.user.port.out.GetUserPort;
import taskdb.taskdb.domain.answer.entity.Answer;
import taskdb.taskdb.domain.answerLike.entity.AnswerUnLike;
import taskdb.taskdb.domain.answerLike.exception.DuplicateAnswerUnLikeException;
import taskdb.taskdb.domain.user.entity.User;

@Service
@RequiredArgsConstructor
@Transactional
public class AnswerUnLikeService implements AnswerUnLikeUseCase {
    private final GetUserPort getUserPort;
    private final GetAnswerPort getAnswerPort;
    private final ExistAnswerUnLikePort existAnswerUnLikePort;
    private final ExistAnswerLikePort existAnswerLikePort;
    private final AnswerUnLikeMapper answerUnLikeMapper;
    private final SaveAnswerUnLikePort saveAnswerUnLikePort;
    private final DeleteAnswerLikePort deleteAnswerLikePort;

    @Override
    public void unLike(Long id) {
        User user = getUserPort.getCurrentUser();
        Answer answer = getAnswerPort.getAnswer(id);
        checkUnLike(answer, user);
        AnswerUnLike answerUnLike = answerUnLikeMapper.toEntity(answer, user);
        saveAnswerUnLikePort.save(answerUnLike);
        checkUnLikeCount(answer, user);
        answer.downLikeCount();
    }

    private void checkUnLike(Answer answer, User user) {
        if(existAnswerUnLikePort.hasAnswerUnLike(answer, user)) {
            throw new DuplicateAnswerUnLikeException();
        }
    }

    private void checkUnLikeCount(Answer answer, User user) {
        if(existAnswerLikePort.hasAnswerLike(answer, user)) {
            answer.downLikeCount();
            deleteAnswerLikePort.delete(answer, user);
        }
    }
}
