package taskdb.taskdb.domain.like.answerLike.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taskdb.taskdb.domain.like.LikeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/answerLike")
public class AnswerLikeApiController {
    private final LikeService likeService;

    @PutMapping("/{id}")
    public boolean like(@PathVariable("id") Long id) {
        return likeService.like(id);
    }
}
