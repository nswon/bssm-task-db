package taskdb.taskdb.application.question.dto;

import lombok.Builder;
import lombok.Getter;
import taskdb.taskdb.application.answer.dto.AnswersResponseDto;
import taskdb.taskdb.application.comment.dto.CommentsResponseDto;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.question.entity.QuestionStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class QuestionDetailResponse {
    private String title;
    private QuestionStatus status;
    private String userImage;
    private String nickname;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private int viewCount;
    private int likeCount;
    private String content;
    private List<CommentsResponseDto> comments;
    private List<AnswersResponseDto> answers;

    public QuestionDetailResponse() {
    }

    @Builder
    public QuestionDetailResponse(Question question) {
        this.title = question.getTitle();
        this.status = question.getQuestionStatus();
        this.userImage = question.getUser().getImgUrl();
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
