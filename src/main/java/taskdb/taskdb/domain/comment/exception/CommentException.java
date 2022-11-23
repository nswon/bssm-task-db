package taskdb.taskdb.domain.comment.exception;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import taskdb.taskdb.global.exception.application.BaseException;
import taskdb.taskdb.global.exception.application.BaseExceptionType;

@AllArgsConstructor
@RequiredArgsConstructor
public class CommentException extends BaseException {

    private final BaseExceptionType exceptionType;

    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
