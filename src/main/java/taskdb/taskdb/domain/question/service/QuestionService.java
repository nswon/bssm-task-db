package taskdb.taskdb.domain.question.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.question.domain.Category;
import taskdb.taskdb.domain.question.domain.Question;
import taskdb.taskdb.domain.question.repository.QuestionQuerydslRepository;
import taskdb.taskdb.domain.question.repository.QuestionRepository;
import taskdb.taskdb.domain.question.facade.QuestionFacade;
import taskdb.taskdb.domain.question.dto.QuestionCreateRequestDto;
import taskdb.taskdb.domain.question.dto.QuestionResponseDto;
import taskdb.taskdb.domain.question.dto.QuestionsResponseDto;
import taskdb.taskdb.domain.question.dto.QuestionUpdateRequestDto;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.facade.UserFacade;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserFacade userFacade;
    private final QuestionFacade questionFacade;
    private final QuestionQuerydslRepository questionQuerydslRepository;

    @Transactional
    public void create(QuestionCreateRequestDto requestDto) {
        User user = userFacade.getCurrentUser();
        Question question = Question.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .category(requestDto.getCategory())
                .build();
        question.confirmUser(user);
        question.openQuestion();
        questionRepository.save(question);
    }

    public List<QuestionsResponseDto> getQuestions() {
        return questionRepository.findAll().stream()
                .map(QuestionsResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public QuestionResponseDto getQuestion(Long id) {
        Question question = questionFacade.getQuestionById(id);
        questionFacade.addViewCountByVisit(question);
        return QuestionResponseDto.builder()
                .question(question)
                .build();
    }

    @Transactional
    public void update(Long id, QuestionUpdateRequestDto requestDto) {
        User user = userFacade.getCurrentUser();
        Question question = questionFacade.getQuestionById(id);
        User writer = question.getUser();
        userFacade.checkDifferentUser(user, writer);
        question.update(requestDto.getTitle(), requestDto.getContent());
    }

    @Transactional
    public void delete(Long id) {
        User user = userFacade.getCurrentUser();
        Question question = questionFacade.getQuestionById(id);
        User writer = question.getUser();
        userFacade.checkDifferentUser(user, writer);
        questionRepository.delete(question);
    }

    public List<QuestionsResponseDto> searchByTitleOrId(String keyword) {
        return questionQuerydslRepository.getQuestionByTitleOrId(keyword).stream()
                .map(QuestionsResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<QuestionsResponseDto> getQuestionsByCategory(Category category) {
        return questionRepository.findByCategory(category).stream()
                .map(QuestionsResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<QuestionsResponseDto> getQuestionsByGrade(int grade) {
        return questionQuerydslRepository.getQuestionByGrade(grade).stream()
                .map(QuestionsResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<QuestionsResponseDto> getOpenQuestions() {
        return questionRepository.findAll().stream()
                .filter(Question::isOpen)
                .map(QuestionsResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<QuestionsResponseDto> getCloseQuestions() {
        return questionRepository.findAll().stream()
                .filter(Question::isClose)
                .map(QuestionsResponseDto::new)
                .collect(Collectors.toList());
    }
}
