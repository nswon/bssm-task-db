package taskdb.taskdb.application.question.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.application.like.port.out.ExistQuestionLikePort;
import taskdb.taskdb.application.question.dto.*;
import taskdb.taskdb.application.question.port.in.*;
import taskdb.taskdb.application.question.port.out.*;
import taskdb.taskdb.domain.question.entity.Category;
import taskdb.taskdb.domain.question.entity.Content;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.question.entity.Title;
import taskdb.taskdb.domain.user.entity.User;
import taskdb.taskdb.application.user.port.out.GetUserPort;
import taskdb.taskdb.domain.user.exception.DifferentUserException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService implements
        QuestionSaveUseCase, QuestionGetUseCase, QuestionUpdateUseCase, QuestionDeleteUseCase, QuestionRankUseCase {
    private final GetUserPort getUserPort;
    private final QuestionMapper questionMapper;
    private final SaveQuestionPort saveQuestionPort;
    private final GetQuestionPort getQuestionPort;
    private final DeleteQuestionPort deleteQuestionPort;
    private final SaveVisitQuestionPort saveVisitQuestionPort;
    private final GetVisitQuestionPort getVisitQuestionPort;
    private final ExistQuestionLikePort existQuestionLikePort;

    @Override
    @Transactional
    public Question save(QuestionCreateRequestDto requestDto) {
        User user = getUserPort.getCurrentUser();
        Question question = questionMapper.of(requestDto, user);
        question.openQuestion();
        return saveQuestionPort.save(question);
    }

    @Override
    public QuestionAllResponseDto getQuestions() {
        List<Question> questions = getQuestionPort.getQuestions();
        return questionMapper.of(questions);
    }

    @Transactional
    public QuestionDetailResponse getQuestion(Long id) {
        User user = getUserPort.getCurrentUser();
        Question question = getQuestionPort.getQuestion(id);
        checkViewCount(question);
        boolean hasLike = hasLike(question, user);
        return questionMapper.of(hasLike, question);
    }

    private void checkViewCount(Question question) {
        List<String> questionIds = getVisitQuestionPort.getVisitQuestionIds();
        if(canAddViewCount(questionIds, String.valueOf(question.getId()))) {
            saveVisitQuestionPort.save(question.getId());
            question.addViewCount();
        }
    }

    private boolean hasLike(Question question, User user) {
        return existQuestionLikePort.hasQuestionLike(question, user);
    }

    private boolean canAddViewCount(List<String> questionIds, String questionId) {
        return questionIds.isEmpty() || questionIds.stream()
                .noneMatch(id -> id.equals(questionId));
    }

    @Override
    @Transactional
    public void update(Long id, QuestionUpdateRequestDto requestDto) {
        Question question = getQuestionPort.getQuestion(id);
        checkDifferentUser(question.getUser());
        update(question, requestDto);
    }

    private void update(Question question, QuestionUpdateRequestDto requestDto) {
        Title title = Title.of(requestDto.getTitle());
        Content content = Content.of(requestDto.getContent());
        question.update(title, content);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Question question = getQuestionPort.getQuestion(id);
        checkDifferentUser(question.getUser());
        deleteQuestionPort.delete(question);
    }

    public QuestionAllResponseDto getQuestionsByKeyword(String keyword) {
        List<Question> questions = getQuestionPort.getQuestionsByKeyword(keyword);
        return questionMapper.of(questions);
    }

    @Override
    public QuestionAllResponseDto getQuestionsByCategory(String command) {
        List<Question> questions = getQuestionPort.getQuestionsByCategory(command);
        return questionMapper.of(questions);
    }

    @Override
    public QuestionAllResponseDto getQuestionsByGrade(int grade) {
        List<Question> questions = getQuestionPort.getQuestionsByGrade(grade);
        return questionMapper.of(questions);
    }

    @Override
    public QuestionAllResponseDto getQuestionsByStatus(String command) {
        List<Question> questions = getQuestionPort.getQuestionsByStatus(command);
        return questionMapper.of(questions);
    }

    private void checkDifferentUser(User writer) {
        User user = getUserPort.getCurrentUser();
        if(user.isNotCorrectEmail(writer.getEmail())) {
            throw new DifferentUserException();
        }
    }

    @Override
    public List<QuestionsRankResponseDto> rank() {
        double questionTotalCount = 1.0 * getQuestionPort.getQuestions().size();
        return Category.getValues().stream()
                .map(category -> {
                    String subjectName = category.name();
                    String rate = getRate(questionTotalCount, category);
                    return new QuestionsRankResponseDto(subjectName, rate);
                })
                .sorted(Comparator.comparing(QuestionsRankResponseDto::getRate).reversed())
                .collect(Collectors.toList());
    }

    private String getRate(double questionCount, Category category) {
        double subjectCount = getCountByCategory(category);
        double rate = subjectCount/questionCount * 100;
        return String.format("%.2f", rate);
    }

    private double getCountByCategory(Category category) {
        return 1.0 * (int) getQuestionPort.getQuestions().stream()
                .filter(question -> question.getCategory().equals(category))
                .count();
    }
}
