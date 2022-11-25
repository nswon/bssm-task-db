package taskdb.taskdb.domain.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.comment.domain.Comment;
import taskdb.taskdb.domain.like.questionLike.domain.QuestionLike;
import taskdb.taskdb.domain.questions.domain.Question;
import taskdb.taskdb.domain.questions.presentation.dto.response.QuestionsResponseDto;
import taskdb.taskdb.domain.store.domain.QuestionStore;
import taskdb.taskdb.domain.store.presentation.dto.response.StoreQuestionsResponseDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    private int contributionLevel;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private final List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private final List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private final List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<QuestionStore> questionStores = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<QuestionLike> questionLikes = new ArrayList<>();

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

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public List<QuestionsResponseDto> toQuestionsResponseDto() {
        return this.questions.stream()
                .map(QuestionsResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<StoreQuestionsResponseDto> toStoreQuestionsResponseDto() {
        return this.questionStores.stream()
                .map(StoreQuestionsResponseDto::new)
                .collect(Collectors.toList());
    }

    public void addContributionLevel() {
        this.contributionLevel += 1;
    }

    public void addQuestion(QuestionStore question) {
        this.questionStores.add(question);
    }

    public int getAnswerCount() {
        return this.answers.size();
    }

    public int getQuestionCount() {
        return this.questions.size();
    }

    public void addQuestionLike(QuestionLike questionLike) {
        this.questionLikes.add(questionLike);
    }
}
