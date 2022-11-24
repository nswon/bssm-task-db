package taskdb.taskdb.domain.answer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.answer.domain.AnswerRepository;
import taskdb.taskdb.domain.answer.facade.AnswerFacade;
import taskdb.taskdb.domain.answer.presentation.dto.request.AnswerCreateRequestDto;
import taskdb.taskdb.domain.answer.presentation.dto.request.AnswerUpdateRequestDto;
import taskdb.taskdb.domain.questions.domain.Question;
import taskdb.taskdb.domain.questions.facade.QuestionFacade;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.facade.UserFacade;

@Service
@RequiredArgsConstructor
@Transactional
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionFacade questionFacade;
    private final UserFacade userFacade;
    private final AnswerFacade answerFacade;

    public void create(Long id, AnswerCreateRequestDto requestDto) {
        User user = userFacade.getCurrentUser();
        Question question = questionFacade.getQuestionById(id);
        Answer answer = requestDto.toEntity();
        answer.confirmUser(user);
        answer.confirmQuestion(question);
        answer.ongoing();
        answerRepository.save(answer);
    }

    public void update(Long id, AnswerUpdateRequestDto requestDto) {
        User user = userFacade.getCurrentUser();
        Answer answer = answerFacade.getAnswerById(id);
        User writer = answer.getUser();
        userFacade.checkDifferentUser(user, writer);
        answer.update(requestDto.getContent());
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
    }
}
