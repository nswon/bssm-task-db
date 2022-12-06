package taskdb.taskdb.domain.user.domain;

import lombok.Getter;
import taskdb.taskdb.domain.user.exception.NotBsmEmailFormatException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Getter
@Embeddable
public class Email {
    private static final Pattern PATTERN = Pattern.compile("[0-9]{9}@+(bssm.hs.kr)");

    @Column(name = "email", unique = true)
    private String value;

    protected Email() {
    }

    private Email(String value) {
        this.value = value;
    }

    public static Email of(String value) {
        validate(value);
        return new Email(value);
    }

    private static void validate(String value) {
        if(!PATTERN.matcher(value).matches()) {
            throw new NotBsmEmailFormatException();
        }
    }
}
