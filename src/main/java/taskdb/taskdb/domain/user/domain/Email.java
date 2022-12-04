package taskdb.taskdb.domain.user.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import taskdb.taskdb.domain.user.exception.NotBsmEmailFormatException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {
    private static final Pattern PATTERN = Pattern.compile("[0-9]{9}@+(bssm.hs.kr)");

    @Column(name = "email")
    private String value;

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
