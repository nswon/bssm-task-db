package taskdb.taskdb.domain.comment.presentation.dto.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CommentUpdateRequestDto {
    private String content;
}
