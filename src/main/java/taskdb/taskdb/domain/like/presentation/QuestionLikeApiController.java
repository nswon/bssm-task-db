package taskdb.taskdb.domain.like.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taskdb.taskdb.domain.like.service.QuestionLikeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questionLike")
public class QuestionLikeApiController {
    private final QuestionLikeService likeService;

    @PutMapping("/{id}")
    private boolean like(@PathVariable("id") Long id) {
        return likeService.like(id);
    }
}
