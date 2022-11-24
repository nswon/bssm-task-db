package taskdb.taskdb.domain.questions.domain;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import taskdb.taskdb.domain.comment.domain.Comment;
import taskdb.taskdb.domain.user.domain.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "QUESTION")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private int viewCount;

    @Enumerated(EnumType.STRING)
    private QuestionStatus questionStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private Category category;

    //좋아요

    //댓글

    //답변

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Comment> comments = new ArrayList<>();

    @Builder
    public Question(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
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

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void confirmUser(User user) {
        this.user = user;
        user.addQuestion(this);
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
}
