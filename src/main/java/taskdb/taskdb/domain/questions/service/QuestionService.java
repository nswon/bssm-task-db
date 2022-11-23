package taskdb.taskdb.domain.questions.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.questions.domain.Category;
import taskdb.taskdb.domain.questions.domain.Question;
import taskdb.taskdb.domain.questions.domain.QuestionQuerydslRepository;
import taskdb.taskdb.domain.questions.domain.QuestionRepository;
import taskdb.taskdb.domain.questions.facade.QuestionFacade;
import taskdb.taskdb.domain.questions.presentation.dto.request.QuestionCreateRequestDto;
import taskdb.taskdb.domain.questions.presentation.dto.request.QuestionUpdateRequestDto;
import taskdb.taskdb.domain.questions.presentation.dto.response.QuestionResponseDto;
import taskdb.taskdb.domain.questions.presentation.dto.response.QuestionsResponseDto;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.facade.UserFacade;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserFacade userFacade;
    private final QuestionFacade questionFacade;
    private final QuestionQuerydslRepository questionQuerydslRepository;

    public void create(QuestionCreateRequestDto requestDto) {
        User user = userFacade.getCurrentUser();
        Question question = requestDto.toEntity();
        question.confirmUser(user);
        question.openQuestion();
        questionRepository.save(question);
    }

    @Transactional(readOnly = true)
    public List<QuestionsResponseDto> getQuestions() {
        return questionRepository.findAll().stream()
                .map(QuestionsResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public QuestionResponseDto getQuestion(Long id) {
        Question question = questionFacade.getQuestionById(id);
        question.addViewCount();
        return QuestionResponseDto.builder()
                .question(question)
                .build();
    }

    public void update(Long id, QuestionUpdateRequestDto requestDto) {
        User user = userFacade.getCurrentUser();
        Question question = questionFacade.getQuestionById(id);
        User writer = question.getUser();
        questionFacade.checkDifferentUser(user, writer);
        question.updateQuestion(requestDto.getTitle(), requestDto.getContent());
    }

    public void delete(Long id) {
        User user = userFacade.getCurrentUser();
        Question question = questionFacade.getQuestionById(id);
        User writer = question.getUser();
        questionFacade.checkDifferentUser(user, writer);
        questionRepository.delete(question);
    }

    @Transactional(readOnly = true)
    public List<QuestionsResponseDto> searchByTitleOrId(Object keyword) {
        return questionQuerydslRepository.getQuestionByTitleOrId(keyword).stream()
                .map(QuestionsResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<QuestionsResponseDto> getQuestionsByCategory(Category category) {
        return questionRepository.findByCategory(category).stream()
                .map(QuestionsResponseDto::new)
                .collect(Collectors.toList());
    }
}
