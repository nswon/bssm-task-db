package taskdb.taskdb.domain.answerLike.entity;

import lombok.Builder;
import lombok.Getter;
import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.user.entity.User;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "ANSWER_LIKE")
public class AnswerLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    protected AnswerLike() {
    }

    @Builder
    public AnswerLike(Answer answer, User user) {
        this.answer = answer;
        answer.addAnswerLike(this);
        this.user = user;
        user.addAnswerLike(this);
    }
}
