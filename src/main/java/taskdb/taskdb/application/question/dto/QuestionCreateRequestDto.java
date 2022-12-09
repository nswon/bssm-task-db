package taskdb.taskdb.application.question.dto;

import lombok.Getter;
import lombok.ToString;
import taskdb.taskdb.domain.question.entity.Category;

@Getter
@ToString
public class QuestionCreateRequestDto {
    private String title;
    private String content;
    private Category category;

    public QuestionCreateRequestDto() {
    }

    public QuestionCreateRequestDto(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }
}
