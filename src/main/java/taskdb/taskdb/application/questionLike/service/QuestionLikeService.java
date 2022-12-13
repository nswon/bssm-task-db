package taskdb.taskdb.application.questionLike.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.application.questionLike.dto.QuestionLikeMapper;
import taskdb.taskdb.application.questionLike.port.in.QuestionLikeUseCase;
import taskdb.taskdb.application.questionLike.port.out.ExistQuestionLikePort;
import taskdb.taskdb.application.questionLike.port.out.GetQuestionLikePort;
import taskdb.taskdb.application.questionLike.port.out.SaveQuestionLikePort;
import taskdb.taskdb.application.question.port.out.GetQuestionPort;
import taskdb.taskdb.application.questionLike.port.out.DeleteQuestionUnLikePort;
import taskdb.taskdb.domain.questionLike.entity.QuestionLike;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.questionLike.exception.DuplicateQuestionLikeException;
import taskdb.taskdb.domain.user.entity.User;
import taskdb.taskdb.application.user.port.out.GetUserPort;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionLikeService implements QuestionLikeUseCase {
    private final GetUserPort getUserPort;
    private final GetQuestionPort getQuestionPort;
    private final SaveQuestionLikePort saveQuestionLikePort;
    private final ExistQuestionLikePort existQuestionLikePort;
    private final QuestionLikeMapper questionLikeMapper;
    private final DeleteQuestionUnLikePort deleteQuestionUnLikePort;

    @Override
    public void like(Long id) {
        User user = getUserPort.getCurrentUser();
        Question question = getQuestionPort.getQuestion(id);
        checkLike(question, user);
        QuestionLike questionLike = questionLikeMapper.toEntity(question, user);
        saveQuestionLikePort.save(questionLike);
        deleteQuestionUnLikePort.delete(question, user);
        question.addLikeCount();
    }

    private void checkLike(Question question, User user) {
        if(existQuestionLikePort.hasQuestionLike(question, user)) {
            throw new DuplicateQuestionLikeException();
        }
    }
}
