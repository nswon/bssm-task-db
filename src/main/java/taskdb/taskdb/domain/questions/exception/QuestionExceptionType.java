package taskdb.taskdb.domain.questions.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import taskdb.taskdb.global.exception.application.BaseExceptionType;

@Getter
@AllArgsConstructor
public enum QuestionExceptionType implements BaseExceptionType {

    NOT_FOUND_QUESTION(700, HttpStatus.BAD_REQUEST, "질문글이 존재하지 않습니다."),
    DIFFERENT_USER(701, HttpStatus.BAD_REQUEST, "다른 유저의 질문글입니다.");

    private final int errorCode;
    private final HttpStatus httpStatus;
    private final String errorMessage;

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
