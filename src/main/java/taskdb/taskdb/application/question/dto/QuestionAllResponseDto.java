package taskdb.taskdb.application.question.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class QuestionAllResponseDto {
    private final List<QuestionAllElementResponse> questions;

    public QuestionAllResponseDto(List<QuestionAllElementResponse> questionsElements) {
        this.questions = questionsElements;
    }
}
