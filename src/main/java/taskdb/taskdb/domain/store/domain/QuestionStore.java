package taskdb.taskdb.domain.store.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import taskdb.taskdb.domain.question.domain.Title;
import taskdb.taskdb.domain.user.domain.User;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    public QuestionStore(Long questionId, Title questionTitle) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
    }

    public void confirmUser(User user) {
        this.user = user;
        user.addQuestion(this);
    }
}
