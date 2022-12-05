package taskdb.taskdb.domain.user.domain;

import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;
import taskdb.taskdb.domain.user.exception.InvalidPasswordFormatException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Getter
@Embeddable
public class Password {
    private static final Pattern PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z]).{8,20}$");

    protected Password() {
    }

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
        if(!PATTERN.matcher(value).matches()) {
            throw new InvalidPasswordFormatException();
        }
    }

    public boolean isNotSame(String password) {
        return !value.equals(password);
    }
}
