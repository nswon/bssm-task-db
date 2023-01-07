package taskdb.taskdb.application.comment.port.in;

import taskdb.taskdb.application.comment.dto.CommentCreateRequestDto;

import java.util.UUID;

public interface ReCommentSaveUseCase {
    void save(UUID questionId, UUID ParentId, CommentCreateRequestDto requestDto);
}
