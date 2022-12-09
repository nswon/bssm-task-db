package taskdb.taskdb.adapter.like.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taskdb.taskdb.application.like.port.in.LikeUseCase;
import taskdb.taskdb.application.like.service.QuestionLikeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questionLike")
public class QuestionLikeController {
    private final LikeUseCase likeUseCase;

    @PutMapping("/{id}")
    private boolean like(@PathVariable("id") Long id) {
        return likeUseCase.like(id);
    }
}
