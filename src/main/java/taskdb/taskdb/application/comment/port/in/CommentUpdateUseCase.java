package taskdb.taskdb.application.comment.port.in;

import taskdb.taskdb.application.comment.dto.CommentUpdateRequestDto;

public interface CommentUpdateUseCase {
    void update(Long id, CommentUpdateRequestDto requestDto);
}
