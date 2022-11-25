package taskdb.taskdb.domain.answer.presentation.dto.response;

import lombok.Getter;
import taskdb.taskdb.domain.answer.domain.Answer;

@Getter
public class AnswersResponseDto {
    private final String content;
    private final String nickname;
    private final int likeCount;

    public AnswersResponseDto(Answer answer) {
        this.content = answer.getContent();
        this.nickname = answer.getUser().getNickname();
        this.likeCount = answer.getAnswerLikeCount();
    }
}
