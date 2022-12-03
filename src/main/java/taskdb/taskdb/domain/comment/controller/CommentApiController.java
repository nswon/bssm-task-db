package taskdb.taskdb.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taskdb.taskdb.domain.comment.dto.CommentCreateRequestDto;
import taskdb.taskdb.domain.comment.dto.CommentUpdateRequestDto;
import taskdb.taskdb.domain.comment.service.CommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentApiController {
    private final CommentService commentService;

    @PostMapping("/{id}/new")
    public void create(@PathVariable("id") Long id, @RequestBody CommentCreateRequestDto requestDto) {
        commentService.create(id, requestDto);
    }

    @PostMapping("/{id}/{pId}/new")
    public void createReComment(@PathVariable("id") Long questionId,
                                @PathVariable("pId") Long parentId,
                                @RequestBody CommentCreateRequestDto requestDto) {
        commentService.createReComment(questionId, parentId, requestDto);
    }

    @PutMapping("/{id}/edit")
    public void update(@PathVariable("id") Long id, @RequestBody CommentUpdateRequestDto requestDto) {
        commentService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        commentService.delete(id);
    }
}
