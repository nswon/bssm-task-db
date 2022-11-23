package taskdb.taskdb.domain.comment.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentUpdateRequestDto {
    private final String content;
}
