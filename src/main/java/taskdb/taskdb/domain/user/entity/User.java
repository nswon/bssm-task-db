package taskdb.taskdb.domain.user.entity;

import lombok.Builder;
import lombok.Getter;
import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.comment.domain.Comment;
import taskdb.taskdb.domain.answerLike.entity.AnswerLike;
import taskdb.taskdb.domain.questionLike.entity.QuestionLike;
import taskdb.taskdb.domain.answerLike.entity.AnswerUnLike;
import taskdb.taskdb.domain.questionLike.entity.QuestionUnLike;
import taskdb.taskdb.domain.notification.entity.Notification;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.store.entity.QuestionStore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
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
    private final Set<QuestionStore> questionStores = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<QuestionLike> questionLikes = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<AnswerLike> answerLikes = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Notification notification;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<QuestionUnLike> questionUnLikes = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<AnswerUnLike> answerUnLikes = new ArrayList<>();

    protected User() {
    }

    @Builder
    public User(Email email, Grade grade, Nickname nickname, Bio bio, Password password, Image image, Role role) {
        this.email = email;
        this.grade = grade;
        this.nickname = nickname;
        this.bio = bio;
        this.password = password;
        this.image = image;
        this.role = role;
    }

    public boolean isNotCorrectEmail(String email) {
        return !this.email.getValue().equals(email);
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
        return !image.isDefault() && !image.isEmpty();
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

    public boolean hasNotificationToken() {
        return this.notification != null;
    }

    //== 연관관계 ==//
    public void addQuestionLike(QuestionLike questionLike) {
        this.questionLikes.add(questionLike);
    }

    public void addAnswerLike(AnswerLike answerLike) {
        this.answerLikes.add(answerLike);
    }

    public void addQuestionStore(QuestionStore questionStore) {
        this.questionStores.add(questionStore);
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

    public void addNotification(Notification notification) {
        this.notification = notification;
    }

    public void addQuestionUnLike(QuestionUnLike questionUnLike) {
        questionUnLikes.add(questionUnLike);
    }

    public void addAnswerUnLike(AnswerUnLike answerUnLike) {
        answerUnLikes.add(answerUnLike);
    }
}
