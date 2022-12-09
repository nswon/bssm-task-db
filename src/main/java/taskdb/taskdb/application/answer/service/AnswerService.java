package taskdb.taskdb.application.answer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.application.answer.port.in.AnswerAdoptUseCase;
import taskdb.taskdb.application.answer.port.in.AnswerDeleteUseCase;
import taskdb.taskdb.application.answer.port.in.AnswerSaveUseCase;
import taskdb.taskdb.application.answer.port.in.AnswerUpdateUseCase;
import taskdb.taskdb.application.answer.port.out.DeleteAnswerPort;
import taskdb.taskdb.application.answer.port.out.GetAnswerPort;
import taskdb.taskdb.application.answer.port.out.SaveAnswerPort;
import taskdb.taskdb.application.question.port.out.GetQuestionPort;
import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.answer.domain.Content;
import taskdb.taskdb.application.answer.dto.AnswerCreateRequestDto;
import taskdb.taskdb.application.answer.dto.AnswerUpdateRequestDto;
import taskdb.taskdb.application.notification.service.NotificationService;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.user.entity.User;
import taskdb.taskdb.application.user.port.out.GetUserPort;
import taskdb.taskdb.application.answer.dto.AnswerMapper;
import taskdb.taskdb.domain.user.exception.DifferentUserException;

@Service
@RequiredArgsConstructor
@Transactional
public class AnswerService implements
        AnswerSaveUseCase, AnswerUpdateUseCase, AnswerDeleteUseCase, AnswerAdoptUseCase {
    private final GetUserPort getUserPort;
    private final GetQuestionPort getQuestionPort;
    private final NotificationService notificationService;
    private final AnswerMapper answerMapper;
    private final SaveAnswerPort saveAnswerPort;
    private final GetAnswerPort getAnswerPort;
    private final DeleteAnswerPort deleteAnswerPort;

    @Override
    public void save(Long id, AnswerCreateRequestDto requestDto) {
        User user = getUserPort.getCurrentUser();
        Question question = getQuestionPort.getQuestion(id);
        Answer answer = answerMapper.of(requestDto, user, question);
        answer.ongoing();
        saveAnswerPort.save(answer);
        notificationService.sendMessage(user.getNickname(), question.getUser());
    }

    @Override
    public void update(Long id, AnswerUpdateRequestDto requestDto) {
        Answer answer = getAnswerPort.getAnswer(id);
        checkDifferentUser(answer.getUser());
        updateContent(requestDto.getContent(), answer);
    }

    private void updateContent(String requestContent, Answer answer) {
        Content content = Content.of(requestContent);
        answer.update(content);
    }

    @Override
    public void delete(Long id) {
        Answer answer = getAnswerPort.getAnswer(id);
        checkDifferentUser(answer.getUser());
        deleteAnswerPort.delete(answer);
    }

    @Override
    public void adopt(Long id) {
        Answer answer = getAnswerPort.getAnswer(id);
        Question question = answer.getQuestion();
        checkDifferentUser(question.getUser());
        answer.adopt();
        question.closeQuestion();
        User answeredUser = answer.getUser();
        answeredUser.addContributionLevel();
    }

    private void checkDifferentUser(User writer) {
        User user = getUserPort.getCurrentUser();
        if(user.isNotCorrectEmail(writer.getEmail())) {
            throw new DifferentUserException();
        }
    }
}
