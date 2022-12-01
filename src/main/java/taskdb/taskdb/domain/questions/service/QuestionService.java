package taskdb.taskdb.domain.questions.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.questions.domain.*;
import taskdb.taskdb.domain.questions.facade.QuestionFacade;
import taskdb.taskdb.domain.questions.presentation.dto.request.QuestionCreateRequestDto;
import taskdb.taskdb.domain.questions.presentation.dto.request.QuestionUpdateRequestDto;
import taskdb.taskdb.domain.questions.presentation.dto.response.QuestionResponseDto;
import taskdb.taskdb.domain.questions.presentation.dto.response.QuestionsResponseDto;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.facade.UserFacade;
import taskdb.taskdb.global.redis.RedisService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {
    private static final int ONE_DAY_EXPIRE = 86400;
    private final QuestionRepository questionRepository;
    private final UserFacade userFacade;
    private final QuestionFacade questionFacade;
    private final QuestionQuerydslRepository questionQuerydslRepository;
    private final RedisService redisService;

    @Transactional
    public void create(QuestionCreateRequestDto requestDto) {
        User user = userFacade.getCurrentUser();
        Question question = requestDto.toEntity();
        question.confirmUser(user);
        question.openQuestion();
        questionRepository.save(question);
    }

    public List<QuestionsResponseDto> getQuestions() {
        return questionRepository.findAll().stream()
                .map(QuestionsResponseDto::new)
                .collect(Collectors.toList());
    }

    public QuestionResponseDto getQuestion(Long id) {
        Question question = questionFacade.getQuestionById(id);
//        redisService.addVisitQuestion(String.valueOf(question.getId()));
//        redisService.setVisitQuestion(String.valueOf(question.getId()) + "/", ONE_DAY_EXPIRE);
//        redisService.save(String.valueOf(question.getId()));
//        String questionIds = redisService.getAlreadyVisitedQuestions();
//        String questionId = String.valueOf(question.getId());
//        if(questionIds == null) {
//            redisService.setVisitQuestion(questionId + "/", ONE_DAY_EXPIRE);
//            question.addViewCount();
//            return QuestionResponseDto.builder()
//                    .question(question)
//                    .build();
//        }
//        if(!questionIds.contains(questionId)) {
//            redisService.setVisitQuestion(questionIds + questionId + "/", ONE_DAY_EXPIRE);
//            question.addViewCount();
//        }
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
