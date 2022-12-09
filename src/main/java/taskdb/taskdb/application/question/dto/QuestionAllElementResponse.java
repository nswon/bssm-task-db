package taskdb.taskdb.application.question.dto;

import lombok.Builder;
import lombok.Getter;
import taskdb.taskdb.domain.question.entity.Category;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.question.entity.QuestionStatus;

import java.time.LocalDateTime;

@Getter
public class QuestionAllElementResponse {
    private Long id;
    private QuestionStatus status;
    private String title;
    private Category category;
    private int commentCount;
    private int likeCount;
    private LocalDateTime createdDate;

    public QuestionAllElementResponse() {
    }

    @Builder
    public QuestionAllElementResponse(Question question) {
        this.id = question.getId();
        this.status = question.getQuestionStatus();
        this.title = question.getTitle();
        this.category = question.getCategory();
        this.commentCount = question.getCommentsSize();
        this.likeCount = question.getLikeCount();
        this.createdDate = question.getCreatedDate();
    }
}
