package taskdb.taskdb.application.comment.dto;

import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.comment.entity.Comment;
import taskdb.taskdb.domain.comment.entity.Content;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.user.entity.User;

@Component
public class CommentMapper {
    public Comment of(CommentCreateRequestDto requestDto, User user, Question question) {
        Comment comment = Comment.builder()
                .content(Content.of(requestDto.getContent()))
                .build();
        comment.confirmUser(user);
        comment.confirmQuestion(question);
        return comment;
    }

    public Comment of(CommentCreateRequestDto requestDto, User user, Question question, Comment parent) {
        Comment comment = Comment.builder()
                .content(Content.of(requestDto.getContent()))
                .build();
        comment.confirmUser(user);
        comment.confirmQuestion(question);
        comment.confirmParent(parent);
        return comment;
    }
}
