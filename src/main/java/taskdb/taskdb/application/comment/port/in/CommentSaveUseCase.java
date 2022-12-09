package taskdb.taskdb.application.comment.port.in;

import taskdb.taskdb.application.comment.dto.CommentCreateRequestDto;

public interface CommentSaveUseCase {
    void save(Long id, CommentCreateRequestDto requestDto);
}
