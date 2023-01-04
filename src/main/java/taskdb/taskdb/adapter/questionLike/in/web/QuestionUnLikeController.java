package taskdb.taskdb.adapter.questionLike.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taskdb.taskdb.application.questionLike.port.in.QuestionUnLikeUseCase;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questionUnlike")
public class QuestionUnLikeController {
    private final QuestionUnLikeUseCase questionUnLikeUseCase;

    @PutMapping("/{id}")
    public void unLike(@PathVariable("id") UUID id) {
        questionUnLikeUseCase.unLike(id);
    }
}
