package taskdb.taskdb.domain.question.domain;

import lombok.Getter;
import taskdb.taskdb.domain.question.exception.InvalidContentRangeException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Getter
@Embeddable
public class Content {
    private static final Pattern PATTERN = Pattern.compile("^{1,10000}$");

    @Column(name = "content", columnDefinition = "TEXT")
    private String value;

    protected Content() {
    }

    private Content(String value) {
        this.value = value;
    }

    public static Content of(String value) {
        validate(value);
        return new Content(value);
    }

    private static void validate(String value) {
        if(PATTERN.matcher(value).matches()) {
            throw new InvalidContentRangeException();
        }
    }
}
