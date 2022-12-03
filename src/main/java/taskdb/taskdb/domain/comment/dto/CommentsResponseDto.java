package taskdb.taskdb.domain.comment.dto;

import lombok.Getter;
import taskdb.taskdb.domain.comment.domain.Comment;

import java.time.LocalDateTime;

@Getter
public class CommentsResponseDto {
    private String content;
    private String userImage;
    private String nickname;
    private LocalDateTime createdDate;

    public CommentsResponseDto() {
    }

    public CommentsResponseDto(Comment comment) {
        this.content = comment.getContent();
        this.userImage = comment.getUser().getImage().getUrl();
        this.nickname = comment.getUser().getNickname().getValue();
        this.createdDate = comment.getCreatedDate();
    }
}
