package taskdb.taskdb.domain.comment.domain;

import lombok.Builder;
import lombok.Getter;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.user.entity.User;
import taskdb.taskdb.infrastructure.support.BaseTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "COMMENT")
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    public Comment(Content content, User user, Question question, Comment parent) {
        this.content = content;
        this.user = user;
        user.addComment(this);
        this.question = question;
        question.addComment(this);
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
