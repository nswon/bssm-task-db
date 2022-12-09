package taskdb.taskdb.application.question.dto;

import lombok.Getter;

@Getter
public class QuestionsRankResponseDto {
    private static final String PERCENT = "%";
    private String subjectName;
    private String rate;

    public QuestionsRankResponseDto() {
    }

    public QuestionsRankResponseDto(String subjectName, String rate) {
        this.subjectName = subjectName;
        this.rate = rate + PERCENT;
    }
}
