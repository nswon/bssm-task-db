package taskdb.taskdb.domain.question.dto;

import lombok.Builder;
import lombok.Getter;
import taskdb.taskdb.domain.question.domain.Category;
import taskdb.taskdb.domain.question.domain.Question;
import taskdb.taskdb.domain.question.domain.QuestionStatus;

import java.time.LocalDateTime;

@Getter
public class QuestionsResponseDto {
    private Long id;
    private QuestionStatus status;
    private String title;
    private Category category;
    private int commentCount;
    private int likeCount;
    private LocalDateTime createdDate;

    public QuestionsResponseDto() {
    }

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
