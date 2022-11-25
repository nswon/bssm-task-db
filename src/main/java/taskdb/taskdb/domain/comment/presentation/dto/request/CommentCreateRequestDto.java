package taskdb.taskdb.domain.comment.presentation.dto.request;

import lombok.Getter;
import taskdb.taskdb.domain.comment.domain.Comment;

@Getter
public class CommentCreateRequestDto {
    private String content;

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .build();
    }
}
