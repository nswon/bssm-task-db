package taskdb.taskdb.domain.answer.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AnswerUpdateRequestDto {
    private final String content;
}
