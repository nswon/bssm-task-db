package taskdb.taskdb.application.comment.port.in;

import taskdb.taskdb.application.comment.dto.CommentCreateRequestDto;

public interface ReCommentSaveUseCase {
    void save(Long questionId, Long ParentId, CommentCreateRequestDto requestDto);
}
