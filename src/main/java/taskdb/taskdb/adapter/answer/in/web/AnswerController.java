package taskdb.taskdb.adapter.answer.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taskdb.taskdb.application.answer.dto.AnswerCreateRequestDto;
import taskdb.taskdb.application.answer.dto.AnswerUpdateRequestDto;
import taskdb.taskdb.application.answer.port.in.AnswerAdoptUseCase;
import taskdb.taskdb.application.answer.port.in.AnswerDeleteUseCase;
import taskdb.taskdb.application.answer.port.in.AnswerSaveUseCase;
import taskdb.taskdb.application.answer.port.in.AnswerUpdateUseCase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerSaveUseCase answerSaveUseCase;
    private final AnswerUpdateUseCase answerUpdateUseCase;
    private final AnswerDeleteUseCase answerdeleteUseCase;
    private final AnswerAdoptUseCase answerAdoptUseCase;

    @PostMapping("/{id}/new")
    public void create(@PathVariable("id") Long id, @RequestBody AnswerCreateRequestDto requestDto) {
        answerSaveUseCase.save(id, requestDto);
    }

    @PutMapping("/{id}/edit")
    public void update(@PathVariable("id") Long id, @RequestBody AnswerUpdateRequestDto requestDto) {
        answerUpdateUseCase.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        answerdeleteUseCase.delete(id);
    }

    @PutMapping("/{id}/adopt")
    public void adopt(@PathVariable("id") Long id) {
        answerAdoptUseCase.adopt(id);
    }
}
