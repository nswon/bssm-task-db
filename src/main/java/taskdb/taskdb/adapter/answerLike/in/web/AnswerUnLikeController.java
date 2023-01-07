package taskdb.taskdb.adapter.answerLike.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taskdb.taskdb.application.answerLike.port.in.AnswerUnLikeUseCase;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/answerUnlike")
public class AnswerUnLikeController {
    private final AnswerUnLikeUseCase answerUnLikeUseCase;

    @PutMapping("/{id}")
    public void unLike(@PathVariable("id") UUID id) {
        answerUnLikeUseCase.unLike(id);
    }
}
