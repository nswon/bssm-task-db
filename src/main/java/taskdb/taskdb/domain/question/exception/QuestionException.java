package taskdb.taskdb.domain.question.exception;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import taskdb.taskdb.global.exception.BaseException;
import taskdb.taskdb.global.exception.BaseExceptionType;

@AllArgsConstructor
@RequiredArgsConstructor
public class QuestionException extends BaseException {

    private BaseExceptionType exceptionType;

    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
