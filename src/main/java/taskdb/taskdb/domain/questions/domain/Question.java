package taskdb.taskdb.domain.questions.domain;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import taskdb.taskdb.domain.user.domain.User;

import javax.persistence.*;

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

    //좋아요

    //댓글

    //답변

    @Builder
    public Question(String title, String content) {
        this.title = title;
        this.content = content;
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

    public void updateQuestion(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void confirmUser(User user) {
        this.user = user;
        user.addQuestion(this);
    }
}
