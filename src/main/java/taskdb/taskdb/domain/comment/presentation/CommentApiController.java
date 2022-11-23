package taskdb.taskdb.domain.comment.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taskdb.taskdb.domain.comment.presentation.dto.request.CommentCreateRequestDto;
import taskdb.taskdb.domain.comment.presentation.dto.request.CommentUpdateRequestDto;
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

    @PutMapping("/{id}/edit")
    public void update(@PathVariable("id") Long id, @RequestBody CommentUpdateRequestDto requestDto) {
        commentService.update(id, requestDto);
    }
}
