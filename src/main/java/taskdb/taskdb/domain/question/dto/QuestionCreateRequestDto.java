package taskdb.taskdb.domain.question.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import taskdb.taskdb.domain.question.domain.Category;
import taskdb.taskdb.domain.question.domain.Question;

@Getter
@AllArgsConstructor
@ToString
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
