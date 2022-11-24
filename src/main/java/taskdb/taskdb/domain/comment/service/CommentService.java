package taskdb.taskdb.domain.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.comment.domain.Comment;
import taskdb.taskdb.domain.comment.domain.CommentRepository;
import taskdb.taskdb.domain.comment.facade.CommentFacade;
import taskdb.taskdb.domain.comment.presentation.dto.request.CommentCreateRequestDto;
import taskdb.taskdb.domain.comment.presentation.dto.request.CommentUpdateRequestDto;
import taskdb.taskdb.domain.notification.service.NotificationService;
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
    private final CommentFacade commentFacade;
    private final NotificationService notificationService;

    public void create(Long id, CommentCreateRequestDto requestDto) {
        User user = userFacade.getCurrentUser();
        Question question = questionFacade.getQuestionById(id);
        Comment comment = requestDto.toEntity();
        comment.confirmUser(user);
        comment.confirmQuestion(question);
        commentRepository.save(comment);
        String nickname = user.getNickname();
        notificationService.sendByCreateComment(nickname);
    }

    public void createReComment(Long questionId, Long parentId, CommentCreateRequestDto requestDto) {
        User user = userFacade.getCurrentUser();
        Question question = questionFacade.getQuestionById(questionId);
        Comment parentComment = commentFacade.getCommentById(parentId);
        Comment comment = requestDto.toEntity();
        comment.confirmQuestion(question);
        comment.confirmUser(user);
        comment.confirmParent(parentComment);
        commentRepository.save(comment);
    }

    public void update(Long id, CommentUpdateRequestDto requestDto) {
        User user = userFacade.getCurrentUser();
        Comment comment = commentFacade.getCommentById(id);
        User writer = comment.getUser();
        userFacade.checkDifferentUser(user, writer);
        String content = requestDto.getContent();
        comment.update(content);
    }

    public void delete(Long id) {
        User user = userFacade.getCurrentUser();
        Comment comment = commentFacade.getCommentById(id);
        User writer = comment.getUser();
        userFacade.checkDifferentUser(user, writer);
        commentRepository.delete(comment);
    }
}
