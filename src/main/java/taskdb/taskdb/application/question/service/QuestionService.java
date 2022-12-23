package taskdb.taskdb.application.question.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.application.answer.service.AnswerService;
import taskdb.taskdb.application.questionLike.port.out.ExistQuestionLikePort;
import taskdb.taskdb.application.question.dto.*;
import taskdb.taskdb.application.question.port.in.*;
import taskdb.taskdb.application.question.port.out.*;
import taskdb.taskdb.application.questionLike.port.out.ExistQuestionUnLikePort;
import taskdb.taskdb.application.user.policy.DuplicateUserPolicy;
import taskdb.taskdb.application.user.policy.UserPolicy;
import taskdb.taskdb.domain.answer.entity.Answer;
import taskdb.taskdb.domain.question.entity.Category;
import taskdb.taskdb.domain.question.entity.Content;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.question.entity.Title;
import taskdb.taskdb.domain.user.entity.User;
import taskdb.taskdb.application.user.port.out.GetUserPort;
import taskdb.taskdb.domain.user.exception.DifferentUserException;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
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
    private final ExistQuestionUnLikePort existQuestionUnLikePort;
    private final AnswerService answerService;
    private final UserPolicy userPolicy;

    @Override
    @Transactional
    public Question save(QuestionCreateRequestDto requestDto) {
        User user = getUserPort.getCurrentUser();
        Question question = of(requestDto, user);
        question.openQuestion();
        return saveQuestionPort.save(question);
    }

    private Question of(QuestionCreateRequestDto requestDto, User user) {
        Question question = Question.builder()
                .title(Title.of(requestDto.getTitle()))
                .content(Content.of(requestDto.getContent()))
                .category(requestDto.getCategory())
                .build();
        question.relationUser(user);
        return question;
    }

    @Override
    public List<QuestionAllResponseDto> getQuestions() {
        List<Question> questions = getQuestionPort.getQuestions();
        return questionMapper.of(questions);
    }

    @Transactional
    public QuestionDetailResponse getQuestion(Long id) {
        User user = getUserPort.getCurrentUser();
        Question question = getQuestionPort.getQuestion(id);
        checkViewCount(question);
        return questionMapper.of(
                hasLike(question, user),
                hasUnLike(question, user),
                answerService.getAnswers(question),
                question);
    }

    private void checkViewCount(Question question) {
        boolean hasQuestionId = getVisitQuestionPort.hasQuestionId(question.getId());
        if(!hasQuestionId) {
            saveVisitQuestionPort.save(question.getId());
            question.addViewCount();
        }
    }

    private boolean hasLike(Question question, User user) {
        return existQuestionLikePort.hasQuestionLike(question, user);
    }

    private boolean hasUnLike(Question question, User user) {
        return existQuestionUnLikePort.hasQuestionUnLike(question, user);
    }

    @Override
    @Transactional
    public void update(Long id, QuestionUpdateRequestDto requestDto) {
        User user = getUserPort.getCurrentUser();
        Question question = getQuestionPort.getQuestion(id);
        userPolicy.check(user, question.getUser());
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
        User user = getUserPort.getCurrentUser();
        Question question = getQuestionPort.getQuestion(id);
        userPolicy.check(user, question.getUser());
        deleteQuestionPort.delete(question);
    }

    public List<QuestionAllResponseDto> getQuestionsByKeyword(String keyword) {
        List<Question> questions = getQuestionPort.getQuestionsByKeyword(keyword);
        return questionMapper.of(questions);
    }

    @Override
    public List<QuestionAllResponseDto> getQuestionsByCategory(String command) {
        List<Question> questions = getQuestionPort.getQuestionsByCategory(command);
        return questionMapper.of(questions);
    }

    @Override
    public List<QuestionAllResponseDto> getQuestionsByGrade(int grade) {
        List<Question> questions = getQuestionPort.getQuestionsByGrade(grade);
        return questionMapper.of(questions);
    }

    @Override
    public List<QuestionAllResponseDto> getQuestionsByStatus(String command) {
        List<Question> questions = getQuestionPort.getQuestionsByStatus(command);
        return questionMapper.of(questions);
    }

    @Override
    public List<QuestionsRankResponseDto> rank() {
        double questionTotalCount = 1.0 * getQuestionPort.getQuestions().size();
        return Category.getValues().stream()
                .map(category -> {
                    String rate = getRate(questionTotalCount, category);
                    return new QuestionsRankResponseDto(category.name(), rate);
                })
                .sorted(
                        Comparator.comparing(QuestionsRankResponseDto::getRate).reversed()
                )
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
