package taskdb.taskdb.domain.like.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.like.domain.QuestionLike;
import taskdb.taskdb.domain.like.domain.QuestionLikeRepository;
import taskdb.taskdb.domain.question.domain.Question;
import taskdb.taskdb.domain.question.domain.QuestionRepository;
import taskdb.taskdb.domain.question.facade.QuestionFacade;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.facade.UserFacade;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionLikeService {
    private static final String FOUR_A_M_CORN = "0 0 4 * * *";
    private final QuestionLikeRepository questionLikeRepository;
    private final QuestionRepository questionRepository;
    private final UserFacade userFacade;
    private final QuestionFacade questionFacade;

    public boolean like(Long id) {
        User user = userFacade.getCurrentUser();
        Question question = questionFacade.getQuestionById(id);

        if(questionLikeRepository.existsByQuestionAndUser(question, user)) {
            questionLikeRepository.deleteByQuestionAndUser(question, user);
            question.downLikeCount();
            return true;
        }
        QuestionLike questionLike = QuestionLike.builder()
                .question(question)
                .user(user)
                .build();

        questionLike.addQuestion();
        questionLike.addUser();
        questionLikeRepository.save(questionLike);
        question.addLikeCount();
        return true;
    }

    @Scheduled(cron = FOUR_A_M_CORN)
    public void syncLike() {
        questionRepository.findAll()
                .forEach(this::syncQuestionLike);
    }

    private void syncQuestionLike(Question question) {
        int likeCount = questionLikeRepository.findByQuestion(question).size();
        question.syncLikeCount(likeCount);
    }
}
