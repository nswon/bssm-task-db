package taskdb.taskdb.domain.user.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import taskdb.taskdb.domain.user.exception.InvalidPasswordException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {
    private static final Pattern PATTERN = Pattern.compile("^[0-9a-zA-Z]{8,20}");

    @Column(name = "password")
    private String value;

    private Password(String value) {
        this.value = value;
    }

    public static Password of(PasswordEncoder passwordEncoder, String value) {
        validate(value);
        return new Password(passwordEncoder.encode(value));
    }

    private static void validate(String value) {
        if(PATTERN.matcher(value).matches()) {
            throw new InvalidPasswordException();
        }
    }

    public boolean checkPassword(PasswordEncoder passwordEncoder, String password) {
        return !passwordEncoder.matches(password, value);
    }
}
