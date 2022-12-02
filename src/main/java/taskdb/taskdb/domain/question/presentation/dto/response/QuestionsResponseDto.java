package taskdb.taskdb.domain.question.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import taskdb.taskdb.domain.question.domain.Category;
import taskdb.taskdb.domain.question.domain.Question;
import taskdb.taskdb.domain.question.domain.QuestionStatus;

import java.time.LocalDateTime;

@Getter
public class QuestionsResponseDto {
    private final Long id;
    private final QuestionStatus status;
    private final String title;
    private final Category category;
    private final int commentCount;
    private final int likeCount;
    private final LocalDateTime createdDate;

    @Builder
    public QuestionsResponseDto(Question question) {
        this.id = question.getId();
        this.status = question.getQuestionStatus();
        this.title = question.getTitle();
        this.category = question.getCategory();
        this.commentCount = question.getCommentsSize();
        this.likeCount = question.getLikeCount();
        this.createdDate = question.getCreatedDate();
    }
}
