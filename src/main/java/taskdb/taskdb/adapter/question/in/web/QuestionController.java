package taskdb.taskdb.adapter.question.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taskdb.taskdb.application.question.dto.QuestionAllResponseDto;
import taskdb.taskdb.application.question.dto.QuestionCreateRequestDto;
import taskdb.taskdb.application.question.dto.QuestionDetailResponse;
import taskdb.taskdb.application.question.dto.QuestionUpdateRequestDto;
import taskdb.taskdb.application.question.port.in.QuestionDeleteUseCase;
import taskdb.taskdb.application.question.port.in.QuestionGetUseCase;
import taskdb.taskdb.application.question.port.in.QuestionSaveUseCase;
import taskdb.taskdb.application.question.port.in.QuestionUpdateUseCase;
import taskdb.taskdb.global.support.Result;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private final QuestionSaveUseCase questionSaveUseCase;
    private final QuestionGetUseCase questionGetUseCase;
    private final QuestionUpdateUseCase questionUpdateUseCase;
    private final QuestionDeleteUseCase questionDeleteUserCase;

    @PostMapping("/new")
    public void create(@RequestBody @Valid QuestionCreateRequestDto requestDto) {
        questionSaveUseCase.save(requestDto);
    }

    @GetMapping("")
    public Result getQuestions() {
        QuestionAllResponseDto questions = questionGetUseCase.getQuestions();
        return new Result(questions);
    }

    @GetMapping("/{id}")
    public QuestionDetailResponse getQuestion(@PathVariable("id") Long id) {
        return questionGetUseCase.getQuestion(id);
    }

    @PutMapping("/{id}/edit")
    public void update(@PathVariable("id") Long id, @RequestBody QuestionUpdateRequestDto requestDto) {
        questionUpdateUseCase.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        questionDeleteUserCase.delete(id);
    }

    @GetMapping("/search")
    public Result searchByTitleOrId(@RequestParam("q") String keyword) {
        QuestionAllResponseDto questions = questionGetUseCase.getQuestionsByKeyword(keyword);
        return new Result(questions);
    }

    @GetMapping("/categories")
    public Result getQuestionsByCategory(@RequestParam("c") String command) {
        QuestionAllResponseDto questions = questionGetUseCase.getQuestionsByCategory(command);
        return new Result(questions);
    }

    @GetMapping("/grade")
    public Result getQuestionsByGrade(@RequestParam("g") int grade) {
        QuestionAllResponseDto questions = questionGetUseCase.getQuestionsByGrade(grade);
        return new Result(questions);
    }

    @GetMapping("/status")
    public Result getQuestionsByStatus(@RequestParam("status") String command) {
        QuestionAllResponseDto openQuestions = questionGetUseCase.getQuestionsByStatus(command);
        return new Result(openQuestions);
    }
}
