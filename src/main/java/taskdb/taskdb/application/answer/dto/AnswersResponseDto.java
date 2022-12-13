package taskdb.taskdb.application.answer.dto;

import lombok.Getter;
import taskdb.taskdb.domain.answer.domain.Answer;

import java.time.LocalDateTime;

@Getter
public class AnswersResponseDto {
    private Long id;
    private String content;
    private String userImage;
    private String nickname;
    private Long userId;
    private int likeCount;
    private LocalDateTime createdDate;
    private String status;
    private boolean hasLike;
    private boolean hasUnLike;

    public AnswersResponseDto() {
    }

    public AnswersResponseDto(boolean hasLike, boolean hasUnLike, Answer answer) {
        this.id = answer.getId();
        this.content = answer.getContent();
        this.userImage = answer.getUser().getImgUrl();
        this.nickname = answer.getUser().getNickname();
        this.userId = answer.getUser().getId();
        this.likeCount = answer.getLikeCount();
        this.createdDate = answer.getCreatedDate();
        this.status = answer.getChoose().getValue();
        this.hasLike = hasLike;
        this.hasUnLike = hasUnLike;
    }
}
