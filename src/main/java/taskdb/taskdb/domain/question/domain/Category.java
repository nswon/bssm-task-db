package taskdb.taskdb.domain.question.domain;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum Category {
    JAVA,
    DB,
    C_DOUBLE_PLUS,
    CERTIFICATE,
    OTHERS;

    public static List<Category> getValues() {
        return Arrays.stream(values())
                .collect(Collectors.toList());
    }
}
