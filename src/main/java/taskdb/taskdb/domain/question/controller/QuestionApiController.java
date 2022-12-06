package taskdb.taskdb.domain.question.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taskdb.taskdb.domain.question.domain.Category;
import taskdb.taskdb.domain.question.domain.QuestionStatus;
import taskdb.taskdb.domain.question.dto.*;
import taskdb.taskdb.domain.question.service.QuestionService;
import taskdb.taskdb.global.support.Result;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionApiController {
    private final QuestionService questionService;

    @PostMapping("/new")
    public void create(@RequestBody @Valid QuestionCreateRequestDto requestDto) {
        questionService.create(requestDto);
    }

    @GetMapping("")
    public Result getQuestions() {
        QuestionAllResponseDto questions = questionService.getQuestions();
        return new Result(questions);
    }

    @GetMapping("/{id}")
    public QuestionDetailResponse getQuestion(@PathVariable("id") Long id) {
        return questionService.getQuestion(id);
    }

    @PutMapping("/{id}/edit")
    public void update(@PathVariable("id") Long id, @RequestBody QuestionUpdateRequestDto requestDto) {
        questionService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        questionService.delete(id);
    }

    @GetMapping("/search")
    public Result searchByTitleOrId(@RequestParam("q") String keyword) {
        QuestionAllResponseDto questions = questionService.searchByTitleOrId(keyword);
        return new Result(questions);
    }

    @GetMapping("/categories")
    public Result getQuestionsByCategory(@RequestParam("c") String command) {
        QuestionAllResponseDto questions = questionService.getQuestionsByCategory(command);
        return new Result(questions);
    }

    @GetMapping("/grade")
    public Result getQuestionsByGrade(@RequestParam("g") int grade) {
        QuestionAllResponseDto questions = questionService.getQuestionsByGrade(grade);
        return new Result(questions);
    }

    @GetMapping("/status")
    public Result getQuestionsByStatus(@RequestParam("status") String command) {
        QuestionAllResponseDto openQuestions = questionService.getQuestionsByStatus(command);
        return new Result(openQuestions);
    }
}
