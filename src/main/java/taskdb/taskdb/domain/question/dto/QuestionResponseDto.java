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
    private String title;
    private QuestionStatus status;
    private String nickname;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private int viewCount;
    private int likeCount;
    private String content;
    private List<CommentsResponseDto> comments;
    private List<AnswersResponseDto> answers;

    public QuestionResponseDto() {
    }

    @Builder
    public QuestionResponseDto(Question question) {
        this.title = question.getTitle();
        this.status = question.getQuestionStatus();
        this.nickname = question.getUser().getNickname();
        this.createdDate = question.getCreatedDate();
        this.modifiedDate = question.getModifiedDate();
        this.viewCount = question.getViewCount();
        this.likeCount = question.getLikeCount();
        this.content = question.getContent();
        this.comments = question.toCommentsResponseDto();
        this.answers = question.toAnswersResponseDto();
    }
}
