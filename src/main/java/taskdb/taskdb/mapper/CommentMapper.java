package taskdb.taskdb.mapper;

import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.comment.domain.Comment;
import taskdb.taskdb.domain.comment.domain.Content;
import taskdb.taskdb.domain.comment.dto.CommentCreateRequestDto;
import taskdb.taskdb.domain.question.domain.Question;
import taskdb.taskdb.domain.user.domain.User;

@Component
public class CommentMapper {
    public Comment of(CommentCreateRequestDto requestDto, User user, Question question) {
        return Comment.builder()
                .content(Content.of(requestDto.getContent()))
                .user(user)
                .question(question)
                .build();
    }

    public Comment of(CommentCreateRequestDto requestDto, User user, Question question, Comment parent) {
        return Comment.builder()
                .content(Content.of(requestDto.getContent()))
                .user(user)
                .question(question)
                .parent(parent)
                .build();
    }
}
