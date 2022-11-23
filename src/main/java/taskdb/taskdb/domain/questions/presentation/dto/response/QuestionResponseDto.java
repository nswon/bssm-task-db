package taskdb.taskdb.domain.questions.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import taskdb.taskdb.domain.questions.domain.Question;
import taskdb.taskdb.domain.questions.domain.QuestionStatus;

@Getter
public class QuestionResponseDto {
    private final String title;
    private final QuestionStatus status;
    //등록한 날짜
    //수정한 날짜
    private final int viewCount;
    //좋아요 수
    private final String content;
    //댓글들
    //답변들

    @Builder
    public QuestionResponseDto(Question question) {
        this.title = question.getTitle();
        this.status = question.getQuestionStatus();
        this.viewCount = question.getViewCount();
        this.content = question.getContent();
    }
}
