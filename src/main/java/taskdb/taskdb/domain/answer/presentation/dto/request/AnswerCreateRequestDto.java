package taskdb.taskdb.domain.answer.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import taskdb.taskdb.domain.answer.domain.Answer;

@Getter
@AllArgsConstructor
public class AnswerCreateRequestDto {
    private final String content;

    public Answer toEntity() {
        return Answer.builder()
                .content(content)
                .build();
    }
}
