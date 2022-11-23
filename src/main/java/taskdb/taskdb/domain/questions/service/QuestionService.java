package taskdb.taskdb.domain.questions.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.questions.domain.Question;
import taskdb.taskdb.domain.questions.domain.QuestionRepository;
import taskdb.taskdb.domain.questions.presentation.dto.request.QuestionCreateRequestDto;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.facade.UserFacade;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserFacade userFacade;

    public void create(QuestionCreateRequestDto requestDto) {
        User user = userFacade.getCurrentUser();
        Question question = requestDto.toEntity();
        question.confirmUser(user);
        question.openQuestion();
        questionRepository.save(question);
    }
}
