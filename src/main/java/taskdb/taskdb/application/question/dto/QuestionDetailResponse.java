package taskdb.taskdb.application.question.dto;

import lombok.Builder;
import lombok.Getter;
import taskdb.taskdb.application.answer.dto.AnswersResponseDto;
import taskdb.taskdb.application.comment.dto.CommentsResponseDto;
import taskdb.taskdb.domain.comment.entity.Comment;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.question.entity.QuestionStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class QuestionDetailResponse {
    private Long id;
    private String title;
    private QuestionStatus status;
    private String userImage;
    private String nickname;
    private Long userId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private int viewCount;
    private int likeCount;
    private String content;
    private List<CommentsResponseDto> comments;
    private List<AnswersResponseDto> answers;
    private boolean hasLike;
    private boolean hasUnLike;

    public QuestionDetailResponse() {
    }

    @Builder
    public QuestionDetailResponse(boolean hasLike,
                                  boolean hasUnLike,
                                  Question question,
                                  List<AnswersResponseDto> answers) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.status = question.getQuestionStatus();
        this.userImage = question.getUser().getImgUrl();
        this.nickname = question.getUser().getNickname();
        this.userId = question.getUser().getId();
        this.createdDate = question.getCreatedDate();
        this.modifiedDate = question.getModifiedDate();
        this.viewCount = question.getViewCount();
        this.likeCount = question.getLikeCount();
        this.content = question.getContent();
        this.comments = toCommentsResponseDto(question.getComments());
        this.answers = answers;
        this.hasLike = hasLike;
        this.hasUnLike = hasUnLike;
    }

    private List<CommentsResponseDto> toCommentsResponseDto(List<Comment> comments) {
        return comments.stream()
                .map(CommentsResponseDto::new)
                .collect(Collectors.toList());
    }
}
