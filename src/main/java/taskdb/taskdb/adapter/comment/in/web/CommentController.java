package taskdb.taskdb.adapter.comment.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taskdb.taskdb.application.comment.dto.CommentCreateRequestDto;
import taskdb.taskdb.application.comment.dto.CommentUpdateRequestDto;
import taskdb.taskdb.application.comment.port.in.CommentDeleteUseCase;
import taskdb.taskdb.application.comment.port.in.CommentSaveUseCase;
import taskdb.taskdb.application.comment.port.in.CommentUpdateUseCase;
import taskdb.taskdb.application.comment.port.in.ReCommentSaveUseCase;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentSaveUseCase commentSaveUseCase;
    private final ReCommentSaveUseCase reCommentSaveUseCase;
    private final CommentUpdateUseCase commentUpdateUseCase;
    private final CommentDeleteUseCase commentDeleteUseCase;

    @PostMapping("/{id}/new")
    public void create(@PathVariable("id") UUID id, @RequestBody CommentCreateRequestDto requestDto) {
        commentSaveUseCase.save(id, requestDto);
    }

    @PostMapping("/{id}/{pId}/new")
    public void createReComment(@PathVariable("id") UUID questionId,
                                @PathVariable("pId") UUID parentId,
                                @RequestBody CommentCreateRequestDto requestDto) {
        reCommentSaveUseCase.save(questionId, parentId, requestDto);
    }

    @PutMapping("/{id}/edit")
    public void update(@PathVariable("id") UUID id, @RequestBody CommentUpdateRequestDto requestDto) {
        commentUpdateUseCase.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id) {
        commentDeleteUseCase.delete(id);
    }
}
