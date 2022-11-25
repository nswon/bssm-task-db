package taskdb.taskdb.domain.comment.presentation.dto.response;

import lombok.Getter;
import taskdb.taskdb.domain.comment.domain.Comment;

import java.time.LocalDateTime;

@Getter
public class CommentsResponseDto {
    private final String content;
    private final String nickname;
    private final LocalDateTime createdDate;

    public CommentsResponseDto(Comment comment) {
        this.content = comment.getContent();
        this.nickname = comment.getUser().getNickname();
        this.createdDate = comment.getCreatedDate();
    }
}
