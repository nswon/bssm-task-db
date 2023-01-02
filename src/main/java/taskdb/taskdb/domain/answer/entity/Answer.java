package taskdb.taskdb.domain.answer.entity;

import lombok.Builder;
import lombok.Getter;
import taskdb.taskdb.common.support.BaseEntity;
import taskdb.taskdb.domain.answerLike.entity.AnswerLike;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.answerLike.entity.AnswerUnLike;
import taskdb.taskdb.domain.user.entity.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "ANSWER")
public class Answer extends BaseEntity {
    @Embedded
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @Enumerated(EnumType.STRING)
    private AnswerChoose choose;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<AnswerLike> answerLikes = new ArrayList<>();

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<AnswerUnLike> answerUnLikes = new ArrayList<>();

    private int likeCount;

    protected Answer() {
    }

    @Builder
    public Answer(Content content) {
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

    public void update(Content content) {
        this.content = content;
    }

    public void adopt() {
        this.choose = AnswerChoose.ADOPT;
    }

    public void addAnswerLike(AnswerLike answerLike) {
        this.answerLikes.add(answerLike);
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

    public boolean isAdopt() {
        return this.choose.equals(AnswerChoose.ADOPT);
    }

    public void addAnswerUnLike(AnswerUnLike answerUnLike) {
        answerUnLikes.add(answerUnLike);
    }
}
