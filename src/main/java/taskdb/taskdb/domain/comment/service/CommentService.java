package taskdb.taskdb.domain.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.comment.domain.Comment;
import taskdb.taskdb.domain.comment.domain.CommentRepository;
import taskdb.taskdb.domain.comment.presentation.dto.request.CommentCreateRequestDto;
import taskdb.taskdb.domain.questions.domain.Question;
import taskdb.taskdb.domain.questions.facade.QuestionFacade;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.facade.UserFacade;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserFacade userFacade;
    private final QuestionFacade questionFacade;

    public void create(Long id, CommentCreateRequestDto requestDto) {
        User user = userFacade.getCurrentUser();
        Question question = questionFacade.getQuestionById(id);
        Comment comment = requestDto.toEntity();
        comment.confirmUser(user);
        comment.confirmQuestion(question);
        commentRepository.save(comment);
    }
}
