package taskdb.taskdb.domain.comment.entity;

import lombok.Builder;
import lombok.Getter;
import taskdb.taskdb.common.support.BaseEntity;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.user.entity.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "COMMENT")
public class Comment extends BaseEntity {
    @Embedded
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    private final List<Comment> child = new ArrayList<>();

    protected Comment() {
    }

    @Builder
    public Comment(Content content) {
        this.content = content;
    }

    public void confirmUser(User user) {
        this.user = user;
        user.addComment(this);
    }

    public void confirmQuestion(Question question) {
        this.question = question;
        question.addComment(this);
    }

    public void confirmParent(Comment parent) {
        this.parent = parent;
        parent.addChild(this);
    }

    public void addChild(Comment child) {
        this.child.add(child);
    }

    public void update(Content content) {
        this.content = content;
    }

    public String getContent() {
        return content.getValue();
    }
}
