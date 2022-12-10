package taskdb.taskdb.domain.user.exception;

import taskdb.taskdb.infrastructure.exception.InternalException;

public class UploadFailedException extends InternalException {
    private static final String MESSAGE = "파일 업로드에 실패하였습니다.";

    public UploadFailedException() {
        super(MESSAGE);
    }
}
