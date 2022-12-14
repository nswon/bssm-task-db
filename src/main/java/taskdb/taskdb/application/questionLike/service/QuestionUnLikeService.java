package taskdb.taskdb.application.questionLike.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.application.questionLike.port.out.DeleteQuestionLikePort;
import taskdb.taskdb.application.question.port.out.GetQuestionPort;
import taskdb.taskdb.application.questionLike.dto.QuestionUnLikeMapper;
import taskdb.taskdb.application.questionLike.port.in.QuestionUnLikeUseCase;
import taskdb.taskdb.application.questionLike.port.out.ExistQuestionLikePort;
import taskdb.taskdb.application.questionLike.port.out.ExistQuestionUnLikePort;
import taskdb.taskdb.application.questionLike.port.out.SaveQuestionUnLikePort;
import taskdb.taskdb.application.user.port.out.GetUserPort;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.questionLike.entity.QuestionUnLike;
import taskdb.taskdb.domain.questionLike.exception.DuplicateQuestionUnLikeException;
import taskdb.taskdb.domain.user.entity.User;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionUnLikeService implements QuestionUnLikeUseCase {
    private final GetUserPort getUserPort;
    private final GetQuestionPort getQuestionPort;
    private final ExistQuestionUnLikePort existQuestionUnLikePort;
    private final QuestionUnLikeMapper questionUnLikeMapper;
    private final SaveQuestionUnLikePort saveQuestionUnLikePort;
    private final DeleteQuestionLikePort deleteQuestionLikePort;
    private final ExistQuestionLikePort existQuestionLikePort;

    @Override
    public void unLike(Long id) {
        User user = getUserPort.getCurrentUser();
        Question question = getQuestionPort.getQuestion(id);
        checkUnLike(question, user);
        QuestionUnLike questionUnLike = questionUnLikeMapper.toEntity(question, user);
        saveQuestionUnLikePort.save(questionUnLike);
        checkUnLikeCount(question, user);
        question.downLikeCount();
    }

    private void checkUnLike(Question question, User user) {
        if(existQuestionUnLikePort.hasQuestionUnLike(question, user)) {
            throw new DuplicateQuestionUnLikeException();
        }
    }

    private void checkUnLikeCount(Question question, User user) {
        if(existQuestionLikePort.hasQuestionLike(question, user)) {
            question.downLikeCount();
            deleteQuestionLikePort.delete(question, user);
        }
    }
}
