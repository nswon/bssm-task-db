package taskdb.taskdb.domain.answer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.answer.domain.Content;
import taskdb.taskdb.domain.answer.repository.AnswerRepository;
import taskdb.taskdb.domain.answer.facade.AnswerFacade;
import taskdb.taskdb.domain.answer.dto.AnswerCreateRequestDto;
import taskdb.taskdb.domain.answer.dto.AnswerUpdateRequestDto;
import taskdb.taskdb.domain.notification.service.NotificationService;
import taskdb.taskdb.domain.question.domain.Question;
import taskdb.taskdb.domain.question.facade.QuestionFacade;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.facade.UserFacade;
import taskdb.taskdb.domain.user.service.RankingService;

@Service
@RequiredArgsConstructor
@Transactional
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionFacade questionFacade;
    private final UserFacade userFacade;
    private final AnswerFacade answerFacade;
    private final NotificationService notificationService;
    private final RankingService rankingService;

    public void create(Long id, AnswerCreateRequestDto requestDto) {
        User user = userFacade.getCurrentUser();
        Question question = questionFacade.getQuestionById(id);
        Answer answer = Answer.builder()
                .content(Content.of(requestDto.getContent()))
                .build();
        answer.confirmUser(user);
        answer.confirmQuestion(question);
        answer.ongoing();
        answerRepository.save(answer);
        String nickname = user.getNickname();
        User questionWriter = question.getUser();
        notificationService.sendByCreateAnswer(nickname, questionWriter);
    }

    public void update(Long id, AnswerUpdateRequestDto requestDto) {
        User user = userFacade.getCurrentUser();
        Answer answer = answerFacade.getAnswerById(id);
        User writer = answer.getUser();
        userFacade.checkDifferentUser(user, writer);
        Content content = Content.of(requestDto.getContent());
        answer.update(content);
    }

    public void delete(Long id) {
        User user = userFacade.getCurrentUser();
        Answer answer = answerFacade.getAnswerById(id);
        User writer = answer.getUser();
        userFacade.checkDifferentUser(user, writer);
        answerRepository.delete(answer);
    }

    public void adopt(Long id) {
        User user = userFacade.getCurrentUser();
        Answer answer = answerFacade.getAnswerById(id);
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
