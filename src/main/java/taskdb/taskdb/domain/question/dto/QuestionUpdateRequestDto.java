package taskdb.taskdb.domain.question.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class QuestionUpdateRequestDto {
    private String title;
    private String content;

    public QuestionUpdateRequestDto() {
    }

    public QuestionUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
