package taskdb.taskdb.domain.answer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.answer.domain.Content;
import taskdb.taskdb.domain.answer.port.AnswerReader;
import taskdb.taskdb.domain.answer.port.AnswerStore;
import taskdb.taskdb.domain.answer.dto.AnswerCreateRequestDto;
import taskdb.taskdb.domain.answer.dto.AnswerUpdateRequestDto;
import taskdb.taskdb.domain.notification.service.NotificationService;
import taskdb.taskdb.domain.question.domain.Question;
import taskdb.taskdb.domain.question.port.QuestionReader;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.facade.UserFacade;
import taskdb.taskdb.domain.user.port.UserReader;
import taskdb.taskdb.domain.user.service.RankingService;
import taskdb.taskdb.mapper.AnswerMapper;

@Service
@RequiredArgsConstructor
@Transactional
public class AnswerService {
    private final QuestionReader questionReader;
    private final UserReader userReader;
    private final UserFacade userFacade;
    private final NotificationService notificationService;
    private final RankingService rankingService;
    private final AnswerMapper answerMapper;
    private final AnswerStore answerStore;
    private final AnswerReader answerReader;

    public void create(Long id, AnswerCreateRequestDto requestDto) {
        User user = userReader.getCurrentUser();
        Question question = questionReader.getQuestionById(id);
        Answer answer = answerMapper.of(requestDto, user, question);
        answer.ongoing();
        answerStore.store(answer);
        notificationService.sendByCreateAnswer(user.getNickname(), question.getUser());
    }

    public void update(Long id, AnswerUpdateRequestDto requestDto) {
        User user = userReader.getCurrentUser();
        Answer answer = answerReader.getAnswerById(id);
        User writer = answer.getUser();
        userFacade.checkDifferentUser(user, writer);
        updateContent(requestDto.getContent(), answer);
    }

    private void updateContent(String requestContent, Answer answer) {
        Content content = Content.of(requestContent);
        answer.update(content);
    }

    public void delete(Long id) {
        User user = userReader.getCurrentUser();
        Answer answer = answerReader.getAnswerById(id);
        User writer = answer.getUser();
        userFacade.checkDifferentUser(user, writer);
        answerStore.delete(answer);
    }

    public void adopt(Long id) {
        User user = userReader.getCurrentUser();
        Answer answer = answerReader.getAnswerById(id);
        Question question = answer.getQuestion();
        User writer = question.getUser();
        userFacade.checkDifferentUser(user, writer);
        answer.adopt();
        question.closeQuestion();
        User answeredUser = answer.getUser();
        answeredUser.addContributionLevel();
        rankingService.updateUser(answeredUser);
    }
}
