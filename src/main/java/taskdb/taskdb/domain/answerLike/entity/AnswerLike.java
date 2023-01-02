package taskdb.taskdb.domain.answerLike.entity;

import lombok.Builder;
import lombok.Getter;
import taskdb.taskdb.common.support.BaseEntity;
import taskdb.taskdb.domain.answer.entity.Answer;
import taskdb.taskdb.domain.user.entity.User;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "ANSWER_LIKE")
public class AnswerLike extends BaseEntity {
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
