package taskdb.taskdb.application.comment.port.in;

import taskdb.taskdb.application.comment.dto.CommentCreateRequestDto;

import java.util.UUID;

public interface CommentSaveUseCase {
    void save(UUID id, CommentCreateRequestDto requestDto);
}
