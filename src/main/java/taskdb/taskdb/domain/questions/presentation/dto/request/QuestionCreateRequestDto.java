package taskdb.taskdb.domain.questions.presentation.dto.request;

import lombok.Builder;
import lombok.Getter;
import taskdb.taskdb.domain.questions.domain.Category;
import taskdb.taskdb.domain.questions.domain.Question;

@Getter
public class QuestionCreateRequestDto {
    private final String title;
    private final String content;
    private final Category category;

    @Builder
    public QuestionCreateRequestDto(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public Question toEntity() {
        return Question.builder()
                .title(title)
                .content(content)
                .category(category)
                .build();
    }
}
