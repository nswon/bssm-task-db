package taskdb.taskdb.application.comment.dto;

import lombok.Getter;
import taskdb.taskdb.domain.comment.entity.Comment;

import java.time.LocalDateTime;

@Getter
public class CommentsResponseDto {
    private Long id;
    private String content;
    private String userImage;
    private String nickname;
    private Long userId;
    private LocalDateTime createdDate;

    public CommentsResponseDto() {
    }

    public CommentsResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.userImage = comment.getUser().getImgUrl();
        this.nickname = comment.getUser().getNickname();
        this.userId = comment.getUser().getId();
        this.createdDate = comment.getCreatedDate();
    }
}
