package taskdb.taskdb.domain.answer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.answer.domain.AnswerRepository;
import taskdb.taskdb.domain.answer.presentation.dto.request.AnswerCreateRequestDto;
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

    public void create(Long id, AnswerCreateRequestDto requestDto) {
        User user = userFacade.getCurrentUser();
        Question question = questionFacade.getQuestionById(id);
        Answer answer = requestDto.toEntity();
        answer.confirmUser(user);
        answer.confirmQuestion(question);
        answer.ongoing();
        answerRepository.save(answer);
    }
}
