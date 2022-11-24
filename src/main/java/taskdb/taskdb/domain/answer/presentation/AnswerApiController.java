package taskdb.taskdb.domain.answer.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taskdb.taskdb.domain.answer.presentation.dto.request.AnswerCreateRequestDto;
import taskdb.taskdb.domain.answer.presentation.dto.request.AnswerUpdateRequestDto;
import taskdb.taskdb.domain.answer.service.AnswerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerApiController {
    private final AnswerService answerService;

    @PostMapping("/{id}/new")
    public void create(@PathVariable("id") Long id, @RequestBody AnswerCreateRequestDto requestDto) {
        answerService.create(id, requestDto);
    }

    @PutMapping("/{id}/edit")
    public void update(@PathVariable("id") Long id, @RequestBody AnswerUpdateRequestDto requestDto) {
        answerService.update(id, requestDto);
    }
}
