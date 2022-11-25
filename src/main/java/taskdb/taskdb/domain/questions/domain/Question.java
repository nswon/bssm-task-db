package taskdb.taskdb.domain.questions.domain;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.answer.presentation.dto.response.AnswersResponseDto;
import taskdb.taskdb.domain.comment.domain.Comment;
import taskdb.taskdb.domain.comment.presentation.dto.response.CommentsResponseDto;
import taskdb.taskdb.domain.like.questionLike.domain.QuestionLike;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.global.entity.BaseTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "QUESTION")
public class Question extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private int viewCount;

    @Enumerated(EnumType.STRING)
    private QuestionStatus questionStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<QuestionLike> questionLikes = new ArrayList<>();

    @Builder
    public Question(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public void addViewCount() {
        this.viewCount += 1;
    }

    public void openQuestion() {
        this.questionStatus = QuestionStatus.OPEN;
    }

    public void closeQuestion() {
        this.questionStatus = QuestionStatus.CLOSE;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void confirmUser(User user) {
        this.user = user;
        user.addQuestion(this);
    }

    public boolean isOpen() {
        return this.questionStatus == QuestionStatus.OPEN;
    }

    public boolean isClose() {
        return this.questionStatus == QuestionStatus.CLOSE;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public int getCommentsSize() {
        return comments.size();
    }

    public List<CommentsResponseDto> toCommentsResponseDto() {
        return this.comments.stream()
                .map(CommentsResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<AnswersResponseDto> toAnswersResponseDto() {
        return this.answers.stream()
                .map(AnswersResponseDto::new)
                .collect(Collectors.toList());
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public void addQuestionLike(QuestionLike questionLike) {
        this.questionLikes.add(questionLike);
    }

    public int getQuestionLikeCount() {
        return this.questionLikes.size();
    }
}
