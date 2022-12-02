package taskdb.taskdb.domain.answer.presentation.dto.response;

import lombok.Getter;
import taskdb.taskdb.domain.answer.domain.Answer;

import java.time.LocalDateTime;

@Getter
public class AnswersResponseDto {
    private final String content;
    private final String userImage;
    private final String nickname;
    private final int likeCount;
    private final LocalDateTime createdDate;

    public AnswersResponseDto(Answer answer) {
        this.content = answer.getContent();
        this.userImage = answer.getUser().getImgUrl();
        this.nickname = answer.getUser().getNickname();
        this.likeCount = answer.getLikeCount();
        this.createdDate = answer.getCreatedDate();
    }
}
