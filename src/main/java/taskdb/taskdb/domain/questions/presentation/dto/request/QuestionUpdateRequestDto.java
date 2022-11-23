package taskdb.taskdb.domain.questions.presentation.dto.request;

import lombok.Builder;
import lombok.Getter;
import taskdb.taskdb.domain.questions.domain.Question;

@Getter
public class QuestionUpdateRequestDto {
    private final String title;
    private final String content;

    @Builder
    public QuestionUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Question toEntity() {
        return Question.builder()
                .title(title)
                .content(content)
                .build();
    }
}