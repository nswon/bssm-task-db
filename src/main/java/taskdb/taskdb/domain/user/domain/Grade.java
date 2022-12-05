package taskdb.taskdb.domain.user.domain;

import lombok.Getter;
import taskdb.taskdb.domain.user.exception.InvalidGradeRangeException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Embeddable
@Getter
public class Grade {
    private static final Pattern PATTERN = Pattern.compile("^{1,3}$");
    @Column(name = "grade")
    private int value;

    protected Grade() {
    }

    private Grade(int value) {
        this.value = value;
    }

    public static Grade of(int value) {
        validate(value);
        return new Grade(value);
    }

    private static void validate(int value) {
        if(PATTERN.matcher(String.valueOf(value)).matches()) {
            throw new InvalidGradeRangeException();
        }
    }
}
