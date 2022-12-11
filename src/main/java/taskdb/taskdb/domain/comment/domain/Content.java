package taskdb.taskdb.domain.comment.domain;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import taskdb.taskdb.domain.comment.exception.InvalidCommentRangeException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Getter
@Embeddable
public class Content {
    private static final Pattern PATTERN = Pattern.compile("^{1,5000}$");

    @Column(name = "content", columnDefinition = "LONGTEXT")
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
            throw new InvalidCommentRangeException();
        }
    }
}
