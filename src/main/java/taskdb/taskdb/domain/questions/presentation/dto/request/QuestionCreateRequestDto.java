package taskdb.taskdb.domain.questions.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import taskdb.taskdb.domain.questions.domain.Category;
import taskdb.taskdb.domain.questions.domain.Question;

@Getter
@AllArgsConstructor
public class QuestionCreateRequestDto {
    private final String title;
    private final String content;
    private final Category category;

    public Question toEntity() {
        return Question.builder()
                .title(title)
                .content(content)
                .category(category)
                .build();
    }
}
