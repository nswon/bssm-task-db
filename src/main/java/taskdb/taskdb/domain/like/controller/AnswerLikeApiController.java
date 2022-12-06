package taskdb.taskdb.domain.like.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taskdb.taskdb.domain.like.service.AnswerLikeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/answerLike")
public class AnswerLikeApiController {
    private final AnswerLikeService likeService;

    @PutMapping("/{id}")
    public void like(@PathVariable("id") Long id) {
        likeService.like(id);
    }
}
