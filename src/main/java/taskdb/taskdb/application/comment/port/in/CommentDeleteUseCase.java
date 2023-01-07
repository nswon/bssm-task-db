package taskdb.taskdb.application.comment.port.in;

import java.util.UUID;

public interface CommentDeleteUseCase {
    void delete(UUID id);
}
