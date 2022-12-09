package taskdb.taskdb.application.question.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.application.question.dto.QuestionAllResponseDto;
import taskdb.taskdb.application.question.dto.QuestionCreateRequestDto;
import taskdb.taskdb.application.question.dto.QuestionDetailResponse;
import taskdb.taskdb.application.question.dto.QuestionUpdateRequestDto;
import taskdb.taskdb.application.question.port.in.QuestionDeleteUseCase;
import taskdb.taskdb.application.question.port.in.QuestionGetUseCase;
import taskdb.taskdb.application.question.port.in.QuestionSaveUseCase;
import taskdb.taskdb.application.question.port.in.QuestionUpdateUseCase;
import taskdb.taskdb.application.question.port.out.*;
import taskdb.taskdb.domain.question.entity.Content;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.question.entity.Title;
import taskdb.taskdb.domain.user.entity.User;
import taskdb.taskdb.application.user.port.out.GetUserPort;
import taskdb.taskdb.domain.user.exception.DifferentUserException;
import taskdb.taskdb.application.question.dto.QuestionMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService implements
        QuestionSaveUseCase, QuestionGetUseCase, QuestionUpdateUseCase, QuestionDeleteUseCase {
    private final GetUserPort getUserPort;
    private final QuestionMapper questionMapper;
    private final SaveQuestionPort saveQuestionPort;
    private final GetQuestionPort getQuestionPort;
    private final DeleteQuestionPort deleteQuestionPort;
    private final SaveVisitQuestionUseCase saveVisitQuestionPort;
    private final GetVisitQuestionUseCase getVisitQuestionUseCase;

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
        Question question = getQuestionPort.getQuestion(id);
        checkViewCount(question);
        return questionMapper.of(question);
    }

    private void checkViewCount(Question question) {
        List<String> questionIds = getVisitQuestionUseCase.getVisitQuestionIds();
        if(canAddViewCount(questionIds, String.valueOf(question.getId()))) {
            saveVisitQuestionPort.save(question.getId());
            question.addViewCount();
        }
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
}
