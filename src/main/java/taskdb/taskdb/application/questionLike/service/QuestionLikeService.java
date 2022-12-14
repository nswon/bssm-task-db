package taskdb.taskdb.application.questionLike.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.application.answerLike.port.out.ExistAnswerUnLikePort;
import taskdb.taskdb.application.questionLike.dto.QuestionLikeMapper;
import taskdb.taskdb.application.questionLike.port.in.QuestionLikeUseCase;
import taskdb.taskdb.application.questionLike.port.out.*;
import taskdb.taskdb.application.question.port.out.GetQuestionPort;
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
    private final ExistQuestionUnLikePort existQuestionUnLikePort;

    @Override
    public void like(Long id) {
        User user = getUserPort.getCurrentUser();
        Question question = getQuestionPort.getQuestion(id);
        checkLike(question, user);
        QuestionLike questionLike = questionLikeMapper.toEntity(question, user);
        saveQuestionLikePort.save(questionLike);
        checkLikeCount(question, user);
        question.addLikeCount();
    }

    private void checkLike(Question question, User user) {
        if(existQuestionLikePort.hasQuestionLike(question, user)) {
            throw new DuplicateQuestionLikeException();
        }
    }

    private void checkLikeCount(Question question, User user) {
        if(existQuestionUnLikePort.hasQuestionUnLike(question, user)) {
            question.addLikeCount();
            deleteQuestionUnLikePort.delete(question, user);
        }
    }
}
