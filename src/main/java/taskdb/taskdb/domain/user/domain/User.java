package taskdb.taskdb.domain.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import taskdb.taskdb.domain.questions.domain.Question;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    private int grade;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Question> questions = new ArrayList<>();

    @Builder
    public User(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public void encodedPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    public void addUserAuthority() {
        this.role = Role.ROLE_USER;
    }

    public boolean isNotCorrectPassword(PasswordEncoder passwordEncoder, String checkPassword) {
        return !passwordEncoder.matches(checkPassword, this.password);
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public boolean isNotCorrectEmail(String checkEmail) {
        return !this.email.equals(checkEmail);
    }

    public void setGradeByParseEmail() {
        final int START_GRADLE = 4;
        final int END_GRADLE = 5;
        String grade = this.email.substring(START_GRADLE, END_GRADLE);
        this.grade = Integer.parseInt(grade);
    }
}
