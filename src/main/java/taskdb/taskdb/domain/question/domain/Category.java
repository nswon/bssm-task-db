package taskdb.taskdb.domain.question.domain;

import lombok.Getter;
import taskdb.taskdb.domain.question.exception.InvalidCategoryException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum Category {
    JAVA("JAVA"),
    DB("DB"),
    C_DOUBLE_PLUS("C_DOUBLE_PLUS"),
    CERTIFICATE("CERTIFICATE"),
    OTHERS("OTHERS");

    private String command;

    Category() {
    }

    Category(String command) {
        this.command = command;
    }

    public static Category toEnum(String command) {
        return Arrays.stream(values())
                .filter(category -> category.command.equals(command))
                .findAny()
                .orElseThrow(InvalidCategoryException::new);
    }
}
