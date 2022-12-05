package taskdb.taskdb.domain.user.domain;

import lombok.Getter;
import taskdb.taskdb.domain.user.exception.InvalidBioRangeException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Getter
@Embeddable
public class Bio {
    private static final String DEFAULT = "비어있음";
    private static final Pattern PATTERN = Pattern.compile("^{1,100}$");

    @Column(name = "bio", columnDefinition = "TEXT")
    private String value;

    protected Bio() {
    }

    private Bio(String value) {
        this.value = value;
    }

    public static Bio of(String value) {
        validate(value);
        return new Bio(value);
    }

    public static Bio createDefault() {
        return new Bio(DEFAULT);
    }

    private static void validate(String value) {
        if(PATTERN.matcher(value).matches()) {
            throw new InvalidBioRangeException();
        }
    }
}
