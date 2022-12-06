package taskdb.taskdb.domain.question.domain;

import taskdb.taskdb.domain.question.exception.InvalidQuestionStatusException;

import java.util.Arrays;

public enum QuestionStatus {
    OPEN("OPEN"),
    CLOSE("CLOSE");

    private String command;

    QuestionStatus() {
    }

    QuestionStatus(String command) {
        this.command = command;
    }

    public static QuestionStatus toEnum(String command) {
        return Arrays.stream(values())
                .filter(status -> status.command.equals(command))
                .findAny()
                .orElseThrow(InvalidQuestionStatusException::new);
    }
}
