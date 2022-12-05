package taskdb.taskdb.domain.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.comment.domain.Comment;
import taskdb.taskdb.domain.like.domain.AnswerLike;
import taskdb.taskdb.domain.like.domain.QuestionLike;
import taskdb.taskdb.domain.question.domain.Question;
import taskdb.taskdb.domain.question.dto.QuestionsResponseDto;
import taskdb.taskdb.domain.store.domain.QuestionStore;
import taskdb.taskdb.domain.store.dto.StoreQuestionsResponseDto;

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

    @Embedded
    private Email email;

    @Embedded
    private Nickname nickname;

    @Embedded
    private Grade grade;

    @Embedded
    private Password password;

    private int contributionLevel;

    @Embedded
    private Image image;

    @Embedded
    private Bio bio;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<AnswerLike> answerLikes = new ArrayList<>();

    @Builder
    public User(Email email, Grade grade, Nickname nickname, Bio bio, Password password) {
        this.email = email;
        this.grade = grade;
        this.nickname = nickname;
        this.bio = bio;
        this.password = password;
    }

    public void addUserAuthority() {
        this.role = Role.ROLE_USER;
    }

    public boolean isNotCorrectEmail(String checkEmail) {
        return !this.email.getValue().equals(checkEmail);
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

    public int getAnswerCount() {
        return this.answers.size();
    }

    public int getQuestionCount() {
        return this.questions.size();
    }

    public boolean canDeleteImage() {
        return !image.isDefault() || !image.isEmpty();
    }

    public String getImagePath() {
        return image.getPath();
    }

    public void updateImage(Image image) {
        this.image = image;
    }

    public void updateNickname(Nickname nickname) {
        this.nickname = nickname;
    }

    public void updateBio(Bio bio) {
        this.bio = bio;
    }

    public String getEmail() {
        return email.getValue();
    }

    public String getNickname() {
        return nickname.getValue();
    }

    public int getGrade() {
        return grade.getValue();
    }

    public String getPassword() {
        return password.getValue();
    }

    public String getImgUrl() {
        return image.getUrl();
    }

    public String getBio() {
        return bio.getValue();
    }

    //== 연관관계 ==//
    public void addQuestionLike(QuestionLike questionLike) {
        this.questionLikes.add(questionLike);
    }

    public void addAnswerLike(AnswerLike answerLike) {
        this.answerLikes.add(answerLike);
    }

    public void addQuestion(QuestionStore question) {
        this.questionStores.add(question);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }
}
