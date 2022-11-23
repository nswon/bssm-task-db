package taskdb.taskdb.domain.comment.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import taskdb.taskdb.domain.questions.domain.Question;
import taskdb.taskdb.domain.user.domain.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "COMMENT")
public class Comment {
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    private final List<Comment> child = new ArrayList<>();

    @Builder
    public Comment(String content) {
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

    public void update(String content) {
        this.content = content;
    }
}
