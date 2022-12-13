package taskdb.taskdb.adapter.questionLike.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taskdb.taskdb.application.questionLike.port.in.QuestionLikeUseCase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questionLike")
public class QuestionLikeController {
    private final QuestionLikeUseCase questionLikeUseCase;

    @PutMapping("/{id}")
    public void like(@PathVariable("id") Long id) {
        questionLikeUseCase.like(id);
    }
}
