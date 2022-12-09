package taskdb.taskdb.application.answer.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AnswerUpdateRequestDto {
    private String content;

    public AnswerUpdateRequestDto() {
    }

    public AnswerUpdateRequestDto(String content) {
        this.content = content;
    }
}
