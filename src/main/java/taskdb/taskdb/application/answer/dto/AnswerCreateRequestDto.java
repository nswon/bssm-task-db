package taskdb.taskdb.application.answer.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AnswerCreateRequestDto {
    private String content;

    public AnswerCreateRequestDto() {
    }

    public AnswerCreateRequestDto(String content) {
        this.content = content;
    }
}
