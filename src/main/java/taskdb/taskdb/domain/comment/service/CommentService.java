package taskdb.taskdb.domain.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.comment.domain.Comment;
import taskdb.taskdb.domain.comment.domain.Content;
import taskdb.taskdb.domain.comment.port.CommentReader;
import taskdb.taskdb.domain.comment.port.CommentStore;
import taskdb.taskdb.domain.comment.dto.CommentCreateRequestDto;
import taskdb.taskdb.domain.comment.dto.CommentUpdateRequestDto;
import taskdb.taskdb.domain.question.domain.Question;
import taskdb.taskdb.domain.question.port.QuestionReader;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.facade.UserFacade;
import taskdb.taskdb.domain.user.port.UserReader;
import taskdb.taskdb.mapper.CommentMapper;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final UserReader userReader;
    private final UserFacade userFacade;
    private final CommentReader commentReader;
    private final QuestionReader questionReader;
    private final CommentStore commentStore;
    private final CommentMapper commentMapper;

    public void create(Long id, CommentCreateRequestDto requestDto) {
        User user = userReader.getCurrentUser();
        Question question = questionReader.getQuestionById(id);
        Comment comment = commentMapper.of(requestDto, user, question);
        commentStore.store(comment);
    }

    public void createReComment(Long questionId, Long parentId, CommentCreateRequestDto requestDto) {
        User user = userReader.getCurrentUser();
        Question question = questionReader.getQuestionById(questionId);
        Comment parentComment = commentReader.getCommentById(parentId);
        Comment comment = commentMapper.of(requestDto, user, question, parentComment);
        commentStore.store(comment);
    }

    public void update(Long id, CommentUpdateRequestDto requestDto) {
        User user = userReader.getCurrentUser();
        Comment comment = commentReader.getCommentById(id);
        User writer = comment.getUser();
        userFacade.checkDifferentUser(user, writer);
        updateContent(requestDto.getContent(), comment);
    }

    private void updateContent(String requestContent, Comment comment) {
        Content content = Content.of(requestContent);
        comment.update(content);
    }

    public void delete(Long id) {
        User user = userReader.getCurrentUser();
        Comment comment = commentReader.getCommentById(id);
        User writer = comment.getUser();
        userFacade.checkDifferentUser(user, writer);
        commentStore.delete(comment);
    }
}
