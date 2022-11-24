package taskdb.taskdb.domain.answer.exception;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import taskdb.taskdb.global.exception.application.BaseException;
import taskdb.taskdb.global.exception.application.BaseExceptionType;

@AllArgsConstructor
@RequiredArgsConstructor
public class AnswerException extends BaseException {
    private BaseExceptionType exceptionType;

    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
