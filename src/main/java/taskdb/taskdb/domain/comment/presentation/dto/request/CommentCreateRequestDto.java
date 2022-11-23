package taskdb.taskdb.domain.comment.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import taskdb.taskdb.domain.comment.domain.Comment;

@Getter
@AllArgsConstructor
public class CommentCreateRequestDto {
    private final String content;

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .build();
    }
}
