package taskdb.taskdb.domain.question.dto;

import lombok.Builder;
import lombok.Getter;
import taskdb.taskdb.domain.answer.dto.AnswersResponseDto;
import taskdb.taskdb.domain.comment.dto.CommentsResponseDto;
import taskdb.taskdb.domain.question.domain.Question;
import taskdb.taskdb.domain.question.domain.QuestionStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class QuestionResponseDto {
    private final String title;
    private final QuestionStatus status;
    private final String nickname;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;
    private final int viewCount;
    private final int likeCount;
    private final String content;
    private final List<CommentsResponseDto> comments;
    private final List<AnswersResponseDto> answers;

    @Builder
    public QuestionResponseDto(Question question) {
        this.title = question.getTitle();
        this.status = question.getQuestionStatus();
        this.nickname = question.getUser().getNickname().getValue();
        this.createdDate = question.getCreatedDate();
        this.modifiedDate = question.getModifiedDate();
        this.viewCount = question.getViewCount();
        this.likeCount = question.getLikeCount();
        this.content = question.getContent();
        this.comments = question.toCommentsResponseDto();
        this.answers = question.toAnswersResponseDto();
    }
}
