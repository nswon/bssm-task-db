package taskdb.taskdb.adapter.answerLike.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taskdb.taskdb.application.answerLike.port.in.AnswerLikeUseCase;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/answerLike")
public class AnswerLikeController {
    private final AnswerLikeUseCase answerLikeUseCase;

    @PutMapping("/{id}")
    public void like(@PathVariable("id") UUID id) {
        answerLikeUseCase.like(id);
    }
}
