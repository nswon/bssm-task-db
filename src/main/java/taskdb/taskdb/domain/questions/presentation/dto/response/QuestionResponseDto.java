package taskdb.taskdb.domain.questions.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import taskdb.taskdb.domain.answer.presentation.dto.response.AnswersResponseDto;
import taskdb.taskdb.domain.comment.presentation.dto.response.CommentsResponseDto;
import taskdb.taskdb.domain.questions.domain.Question;
import taskdb.taskdb.domain.questions.domain.QuestionStatus;

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
