package taskdb.taskdb.domain.store.entity;

import lombok.Builder;
import lombok.Getter;
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

    @Column(name = "question_store_question_id")
    private Long questionId;

    @Column(name = "question_store_question_title")
    private String questionTitle;

    protected QuestionStore() {
    }

    @Builder
    public QuestionStore(Long questionId, String questionTitle) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
    }

    public void confirmUser(User user) {
        this.user = user;
        user.addQuestionStore(this);
    }
}
