package taskdb.taskdb.domain.question.domain;

import lombok.Getter;
import taskdb.taskdb.domain.question.exception.OverflowTitleException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Getter
@Embeddable
public class Title {
    private static final Pattern PATTERN = Pattern.compile("^{1,50}");
    @Column(name = "title")
    private String value;

    protected Title() {
    }

    private Title(String value) {
        this.value = value;
    }

    public static Title of(String value) {
        validate(value);
        return new Title(value);
    }

    private static void validate(String value) {
        if(!PATTERN.matcher(value).matches()) {
            throw new OverflowTitleException();
        }
    }
}
