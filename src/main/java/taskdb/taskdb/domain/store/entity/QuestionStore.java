package taskdb.taskdb.domain.store.entity;

import lombok.Builder;
import lombok.Getter;
import taskdb.taskdb.domain.question.entity.Title;
import taskdb.taskdb.domain.user.entity.User;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "QUESTION_STORE")
public class QuestionStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Long questionId;

    @Embedded
    private Title questionTitle;

    protected QuestionStore() {
    }

    @Builder
    public QuestionStore(Long questionId, Title questionTitle, User user) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.user = user;
        user.addQuestion(this);
    }

    public String getTitle() {
        return questionTitle.getValue();
    }
}
