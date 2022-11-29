package taskdb.taskdb.domain.user.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import taskdb.taskdb.domain.user.exception.UserException;
import taskdb.taskdb.domain.user.exception.UserExceptionType;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Grade {
    private static final Pattern ONLY_NUMBER_REGEX = Pattern.compile("^[0-9]*$");
    private static final Character AT = '@';
    private static final int START_GRADE_SUB = 4;
    private static final int END_GRADE_SUB = 5;
    private static final int EMAIL_SUB_LENGTH = 9;
    private int grade;

    public Grade(String email) {
        String checkEmail = validateSplit(email);
        validateOnlyNumber(checkEmail);
        this.grade = getGradeByEmail(email);
    }

    private String validateSplit(String email) {
        int index = email.indexOf(AT);
        String checkEmail = email.substring(0, index);
        if(checkEmail.length() != EMAIL_SUB_LENGTH) {
            throw new UserException(UserExceptionType.INVALID_EMAIL);
        }
        return checkEmail;
    }

    private void validateOnlyNumber(String email) {
        if(!ONLY_NUMBER_REGEX.matcher(email).matches()) {
            throw new UserException(UserExceptionType.INVALID_EMAIL);
        }
    }

    private int getGradeByEmail(String email) {
        String grade = email.substring(START_GRADE_SUB, END_GRADE_SUB);
        return Integer.parseInt(grade);
    }
}
