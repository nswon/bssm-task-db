package taskdb.taskdb.application.question.dto;

import lombok.Builder;
import lombok.Getter;
import taskdb.taskdb.application.answer.dto.AnswersResponseDto;
import taskdb.taskdb.application.comment.dto.CommentsResponseDto;
import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.comment.domain.Comment;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.question.entity.QuestionStatus;
import taskdb.taskdb.domain.user.entity.User;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
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

    public QuestionDetailResponse() {
    }

    @Builder
    public QuestionDetailResponse(boolean hasLike, Question question) {
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
        this.answers = toAnswersResponseDto(question.getAnswers());
        this.hasLike = hasLike;
    }

    private List<CommentsResponseDto> toCommentsResponseDto(List<Comment> comments) {
        return comments.stream()
                .map(CommentsResponseDto::new)
                .collect(Collectors.toList());
    }

    private List<AnswersResponseDto> toAnswersResponseDto(List<Answer> answers) {
        return answers.stream()
                .sorted(Comparator.comparing(Answer::isAdopt)
                        .thenComparing(Answer::getLikeCount).reversed())
                .map(AnswersResponseDto::new)
                .collect(Collectors.toList());
    }
}
