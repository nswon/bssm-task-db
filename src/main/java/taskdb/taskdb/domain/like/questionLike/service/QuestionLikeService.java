package taskdb.taskdb.domain.like.questionLike.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.like.questionLike.domain.QuestionLike;
import taskdb.taskdb.domain.like.questionLike.domain.QuestionLikeRepository;
import taskdb.taskdb.domain.questions.domain.Question;
import taskdb.taskdb.domain.questions.facade.QuestionFacade;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.facade.UserFacade;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionLikeService {
    private final QuestionLikeRepository questionLikeRepository;
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
}
