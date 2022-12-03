package taskdb.taskdb.domain.answer.dto;

import lombok.Getter;
import lombok.ToString;
import taskdb.taskdb.domain.answer.domain.Answer;

@Getter
@ToString
public class AnswerCreateRequestDto {
    private String content;

    public Answer toEntity() {
        return Answer.builder()
                .content(content)
                .build();
    }
}
