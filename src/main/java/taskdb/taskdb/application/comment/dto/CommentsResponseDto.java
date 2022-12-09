package taskdb.taskdb.application.comment.dto;

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
        this.userImage = comment.getUser().getImgUrl();
        this.nickname = comment.getUser().getNickname();
        this.createdDate = comment.getCreatedDate();
    }
}
