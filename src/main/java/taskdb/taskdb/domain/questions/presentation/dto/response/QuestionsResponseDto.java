package taskdb.taskdb.domain.questions.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import taskdb.taskdb.domain.questions.domain.Category;
import taskdb.taskdb.domain.questions.domain.Question;
import taskdb.taskdb.domain.questions.domain.QuestionStatus;

@Getter
public class QuestionsResponseDto {
    private final Long id;
    private final QuestionStatus status;
    private final String title;
    private final Category category;
    private final int commentCount;
    private final int likeCount;
    //등록 날짜

    @Builder
    public QuestionsResponseDto(Question question) {
        this.id = question.getId();
        this.status = question.getQuestionStatus();
        this.title = question.getTitle();
        this.category = question.getCategory();
        this.commentCount = question.getCommentsSize();
        this.likeCount = question.getQuestionLikeCount();
    }
}
