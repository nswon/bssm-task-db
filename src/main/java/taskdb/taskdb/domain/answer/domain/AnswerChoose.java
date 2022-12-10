package taskdb.taskdb.domain.answer.domain;

import lombok.Getter;

@Getter
public enum AnswerChoose {
    ONGOING("진행중"),
    ADOPT("채택");

    private String value;

    AnswerChoose() {
    }

    AnswerChoose(String value) {
        this.value = value;
    }
}
