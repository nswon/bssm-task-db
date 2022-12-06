package taskdb.taskdb.domain.question.dto;

import java.util.List;

public class QuestionAllResponseDto {
    private List<QuestionAllElementResponse> questions;

    public QuestionAllResponseDto(List<QuestionAllElementResponse> questionsElements) {
        this.questions = questionsElements;
    }
}
