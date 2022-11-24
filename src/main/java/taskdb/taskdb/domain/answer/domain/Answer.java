package taskdb.taskdb.domain.answer.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import taskdb.taskdb.domain.questions.domain.Question;
import taskdb.taskdb.domain.user.domain.User;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ANSWER")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @Enumerated(EnumType.STRING)
    private AnswerChoose choose;

    @Builder
    public Answer(String content) {
        this.content = content;
    }

    public void confirmUser(User user) {
        this.user = user;
        user.addAnswer(this);
    }

    public void confirmQuestion(Question question) {
        this.question = question;
        question.addAnswer(this);
    }

    public void ongoing() {
        this.choose = AnswerChoose.ONGOING;
    }

    public void update(String content) {
        this.content = content;
    }
}
