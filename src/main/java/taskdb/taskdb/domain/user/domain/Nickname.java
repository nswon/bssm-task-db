package taskdb.taskdb.domain.user.domain;

import lombok.Getter;
import taskdb.taskdb.domain.user.exception.InvalidNicknameRangeException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Getter
@Embeddable
public class Nickname {
    private static final Pattern PATTERN = Pattern.compile("^{2,20}$");

    @Column(name = "nickname", unique = true)
    private String value;

    protected Nickname() {
    }

    private Nickname(String value) {
        this.value = value;
    }

    public static Nickname of(String value) {
        validate(value);
        return new Nickname(value);
    }

    private static void validate(String value) {
        if(PATTERN.matcher(value).matches()) {
            throw new InvalidNicknameRangeException();
        }
    }
}
