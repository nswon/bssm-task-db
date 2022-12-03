package taskdb.taskdb.domain.comment.dto;

import lombok.Getter;
import lombok.ToString;
import taskdb.taskdb.domain.comment.domain.Comment;

@Getter
@ToString
public class CommentCreateRequestDto {
    private String content;

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .build();
    }
}
