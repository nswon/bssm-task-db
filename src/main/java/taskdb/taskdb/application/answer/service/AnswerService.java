package taskdb.taskdb.application.answer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.application.answer.dto.AnswersResponseDto;
import taskdb.taskdb.application.answer.port.in.AnswerAdoptUseCase;
import taskdb.taskdb.application.answer.port.in.AnswerDeleteUseCase;
import taskdb.taskdb.application.answer.port.in.AnswerSaveUseCase;
import taskdb.taskdb.application.answer.port.in.AnswerUpdateUseCase;
import taskdb.taskdb.application.answer.port.out.DeleteAnswerPort;
import taskdb.taskdb.application.answer.port.out.GetAnswerPort;
import taskdb.taskdb.application.answer.port.out.SaveAnswerPort;
import taskdb.taskdb.application.answerLike.port.out.ExistAnswerLikePort;
import taskdb.taskdb.application.answerLike.port.out.ExistAnswerUnLikePort;
import taskdb.taskdb.application.question.port.out.GetQuestionPort;
import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.answer.domain.AnswerChoose;
import taskdb.taskdb.domain.answer.domain.Content;
import taskdb.taskdb.application.answer.dto.AnswerCreateRequestDto;
import taskdb.taskdb.application.answer.dto.AnswerUpdateRequestDto;
import taskdb.taskdb.application.notification.service.NotificationService;
import taskdb.taskdb.domain.answer.exception.DuplicateAdoptException;
import taskdb.taskdb.domain.answer.exception.UnauthorizedAdoptAnswerException;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.user.entity.User;
import taskdb.taskdb.application.user.port.out.GetUserPort;
import taskdb.taskdb.application.answer.dto.AnswerMapper;
import taskdb.taskdb.domain.user.exception.DifferentUserException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    private final ExistAnswerLikePort existAnswerLikePort;
    private final ExistAnswerUnLikePort existAnswerUnLikePort;

    @Override
    public void save(Long id, AnswerCreateRequestDto requestDto) {
        User user = getUserPort.getCurrentUser();
        Question question = getQuestionPort.getQuestion(id);
        Answer answer = answerMapper.of(requestDto, user, question);
        answer.ongoing();
        saveAnswerPort.save(answer);
        notificationService.sendMessage(user.getNickname(), question);
    }

    public List<AnswersResponseDto> getAnswers(Question question) {
        User user = getUserPort.getCurrentUser();
        return getAnswerPort.getAnswers(question).stream()
                .sorted(Comparator.comparing(Answer::isAdopt)
                        .thenComparing(Answer::getLikeCount).reversed())
                .map(answer -> new AnswersResponseDto(hasLike(answer, user), hasUnLike(answer, user), answer))
                .collect(Collectors.toList());
    }

    private boolean hasLike(Answer answer, User user) {
        return existAnswerLikePort.hasAnswerLike(answer, user);
    }

    private boolean hasUnLike(Answer answer, User user) {
        return existAnswerUnLikePort.hasAnswerUnLike(answer, user);
    }

    @Override
    public void update(Long id, AnswerUpdateRequestDto requestDto) {
        Answer answer = getAnswerPort.getAnswer(id);
        checkDifferentUser(answer.getUser());
        checkAdoptAnswer(answer);
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
        checkAdoptAnswer(answer);
        deleteAnswerPort.delete(answer);
    }

    @Override
    public void adopt(Long id) {
        Answer answer = getAnswerPort.getAnswer(id);
        Question question = answer.getQuestion();
        checkDifferentUser(question.getUser());
        checkDuplicateAdopt(answer);
        answer.adopt();
        question.closeQuestion();
        User answeredUser = answer.getUser();
        answeredUser.addContributionLevel();
    }

    private void checkDuplicateAdopt(Answer answer) {
        if(answer.isAdopt()) {
            throw new DuplicateAdoptException();
        }
    }

    private void checkDifferentUser(User writer) {
        User user = getUserPort.getCurrentUser();
        if(user.isNotCorrectEmail(writer.getEmail())) {
            throw new DifferentUserException();
        }
    }

    private void checkAdoptAnswer(Answer answer) {
        if(answer.isAdopt()) {
            throw new UnauthorizedAdoptAnswerException();
        }
    }
}
