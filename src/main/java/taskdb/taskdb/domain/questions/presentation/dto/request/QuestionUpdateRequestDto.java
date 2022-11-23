package taskdb.taskdb.domain.questions.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import taskdb.taskdb.domain.questions.domain.Question;

@Getter
@AllArgsConstructor
public class QuestionUpdateRequestDto {
    private final String title;
    private final String content;

    public Question toEntity() {
        return Question.builder()
                .title(title)
                .content(content)
                .build();
    }
}
