package taskdb.taskdb.domain.question.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.question.domain.*;
import taskdb.taskdb.domain.question.dto.*;
import taskdb.taskdb.domain.question.port.QuestionReader;
import taskdb.taskdb.domain.question.port.QuestionStore;
import taskdb.taskdb.domain.question.facade.QuestionFacade;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.facade.UserFacade;
import taskdb.taskdb.domain.user.port.UserReader;
import taskdb.taskdb.mapper.QuestionMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {
    private final UserFacade userFacade;
    private final UserReader userReader;
    private final QuestionFacade questionFacade;
    private final QuestionStore questionStore;
    private final QuestionReader questionReader;
    private final QuestionMapper questionMapper;

    @Transactional
    public void create(QuestionCreateRequestDto requestDto) {
        User user = userReader.getCurrentUser();
        Question question = questionMapper.of(requestDto, user);
        question.openQuestion();
        questionStore.store(question);
    }

    public QuestionAllResponseDto getQuestions() {
        List<Question> questions = questionReader.getQuestions();
        return questionMapper.of(questions);
    }

    @Transactional
    public QuestionDetailResponse getQuestion(Long id) {
        Question question = questionReader.getQuestionById(id);
        questionFacade.checkViewCount(question);
        return questionMapper.of(question);
    }

    @Transactional
    public void update(Long id, QuestionUpdateRequestDto requestDto) {
        User user = userReader.getCurrentUser();
        Question question = questionReader.getQuestionById(id);
        User writer = question.getUser();
        userFacade.checkDifferentUser(user, writer);
        update(question, requestDto);
    }

    private void update(Question question, QuestionUpdateRequestDto requestDto) {
        Title title = Title.of(requestDto.getTitle());
        Content content = Content.of(requestDto.getContent());
        question.update(title, content);
    }

    @Transactional
    public void delete(Long id) {
        User user = userReader.getCurrentUser();
        Question question = questionReader.getQuestionById(id);
        User writer = question.getUser();
        userFacade.checkDifferentUser(user, writer);
        questionStore.delete(question);
    }

    public QuestionAllResponseDto searchByTitleOrId(String keyword) {
        List<Question> questions = questionReader.getQuestionsByKeyword(keyword);
        return questionMapper.of(questions);
    }

    public QuestionAllResponseDto getQuestionsByCategory(String command) {
        List<Question> questions = questionReader.getQuestionsByCategory(command);
        return questionMapper.of(questions);
    }

    public QuestionAllResponseDto getQuestionsByGrade(int grade) {
        List<Question> questions = questionReader.getQuestionsByGrade(grade);
        return questionMapper.of(questions);
    }

    public QuestionAllResponseDto getQuestionsByStatus(String command) {
        List<Question> questions = questionReader.getQuestionsByStatus(command);
        return questionMapper.of(questions);
    }
}
