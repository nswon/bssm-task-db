package taskdb.taskdb.application.comment.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CommentUpdateRequestDto {
    private String content;

    public CommentUpdateRequestDto() {
    }

    public CommentUpdateRequestDto(String content) {
        this.content = content;
    }
}
