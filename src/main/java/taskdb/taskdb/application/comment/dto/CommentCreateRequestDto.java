package taskdb.taskdb.application.comment.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CommentCreateRequestDto {
    private String content;

    public CommentCreateRequestDto() {
    }

    public CommentCreateRequestDto(String content) {
        this.content = content;
    }
}
