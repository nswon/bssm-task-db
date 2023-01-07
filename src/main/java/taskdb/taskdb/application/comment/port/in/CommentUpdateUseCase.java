package taskdb.taskdb.application.comment.port.in;

import taskdb.taskdb.application.comment.dto.CommentUpdateRequestDto;

import java.util.UUID;

public interface CommentUpdateUseCase {
    void update(UUID id, CommentUpdateRequestDto requestDto);
}
