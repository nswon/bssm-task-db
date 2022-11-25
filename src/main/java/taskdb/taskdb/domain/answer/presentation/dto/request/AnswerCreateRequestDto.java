package taskdb.taskdb.domain.answer.presentation.dto.request;

import lombok.Getter;
import taskdb.taskdb.domain.answer.domain.Answer;

@Getter
public class AnswerCreateRequestDto {
    private String content;

    public Answer toEntity() {
        return Answer.builder()
                .content(content)
                .build();
    }
}
