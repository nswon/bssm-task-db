package taskdb.taskdb.domain.question.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taskdb.taskdb.domain.question.domain.Category;
import taskdb.taskdb.domain.question.dto.QuestionCreateRequestDto;
import taskdb.taskdb.domain.question.dto.QuestionResponseDto;
import taskdb.taskdb.domain.question.dto.QuestionsResponseDto;
import taskdb.taskdb.domain.question.dto.QuestionUpdateRequestDto;
import taskdb.taskdb.domain.question.service.QuestionService;
import taskdb.taskdb.global.support.Result;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionApiController {
    private final QuestionService questionService;

    @PostMapping("/new")
    public void create(@RequestBody QuestionCreateRequestDto requestDto) {
        questionService.create(requestDto);
    }

    @GetMapping("")
    public Result getQuestions() {
        List<QuestionsResponseDto> questions = questionService.getQuestions();
        return Result.builder()
                .data(questions)
                .build();
    }

    @GetMapping("/{id}")
    public QuestionResponseDto getQuestion(@PathVariable("id") Long id) {
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
        List<QuestionsResponseDto> questions = questionService.searchByTitleOrId(keyword);
        return Result.builder()
                .data(questions)
                .build();
    }

    @GetMapping("/categories")
    public Result getQuestionsByCategory(@RequestParam("c") Category category) {
        List<QuestionsResponseDto> questions = questionService.getQuestionsByCategory(category);
        return Result.builder()
                .data(questions)
                .build();
    }

    @GetMapping("/grade")
    public Result getQuestionsByGrade(@RequestParam("g") int grade) {
        List<QuestionsResponseDto> questions = questionService.getQuestionsByGrade(grade);
        return Result.builder()
                .data(questions)
                .build();
    }

    @GetMapping("/open")
    public Result getOpenQuestions() {
        List<QuestionsResponseDto> openQuestions = questionService.getOpenQuestions();
        return Result.builder()
                .data(openQuestions)
                .build();
    }

    @GetMapping("/close")
    public Result getCloseQuestions() {
        List<QuestionsResponseDto> closedQuestions = questionService.getCloseQuestions();
        return Result.builder()
                .data(closedQuestions)
                .build();
    }
}
