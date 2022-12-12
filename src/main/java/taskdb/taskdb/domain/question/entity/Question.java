package taskdb.taskdb.domain.question.entity;

import lombok.Builder;
import lombok.Getter;
import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.comment.domain.Comment;
import taskdb.taskdb.domain.like.entity.QuestionLike;
import taskdb.taskdb.domain.user.entity.User;
import taskdb.taskdb.infrastructure.support.BaseTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "QUESTION")
public class Question extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Title title;

    @Embedded
    private Content content;

    @Enumerated(EnumType.STRING)
    private QuestionStatus questionStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private Category category;

    private int viewCount;

    private int likeCount;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<QuestionLike> questionLikes = new ArrayList<>();

    protected Question() {
    }

    @Builder
    public Question(Title title, Content content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public void confirmUser(User user) {
        this.user = user;
        user.addQuestion(this);
    }

    public void addViewCount() {
        this.viewCount += 1;
    }

    public void openQuestion() {
        this.questionStatus = QuestionStatus.OPEN;
    }

    public void closeQuestion() {
        this.questionStatus = QuestionStatus.CLOSE;
    }

    public void update(Title title, Content content) {
        this.title = title;
        this.content = content;
    }

    public boolean isOpen() {
        return this.questionStatus == QuestionStatus.OPEN;
    }

    public boolean isClose() {
        return this.questionStatus == QuestionStatus.CLOSE;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public int getCommentsSize() {
        return comments.size();
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public void addQuestionLike(QuestionLike questionLike) {
        this.questionLikes.add(questionLike);
    }

    public void addLikeCount() {
        this.likeCount += 1;
    }

    public void downLikeCount() {
        this.likeCount -= 1;
    }

    public void syncLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getContent() {
        return content.getValue();
    }

    public String getTitle() {
        return title.getValue();
    }
}
