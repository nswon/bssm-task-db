package taskdb.taskdb.domain.questionLike.entity;

import lombok.Builder;
import lombok.Getter;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.user.entity.User;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "QUESTION_LIKE")
public class QuestionLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    protected QuestionLike() {
    }

    @Builder
    public QuestionLike(Question question, User user) {
        this.question = question;
        this.user = user;
    }

    public void addQuestion() {
        question.addQuestionLike(this);
    }

    public void addUser() {
        user.addQuestionLike(this);
    }
}
