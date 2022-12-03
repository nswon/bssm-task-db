package taskdb.taskdb.domain.question.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taskdb.taskdb.domain.question.domain.Category;
import taskdb.taskdb.domain.question.dto.*;
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
        return new Result(questions);
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
        return new Result(questions);
    }

    @GetMapping("/categories")
    public Result getQuestionsByCategory(@RequestParam("c") Category category) {
        List<QuestionsResponseDto> questions = questionService.getQuestionsByCategory(category);
        return new Result(questions);
    }

    @GetMapping("/grade")
    public Result getQuestionsByGrade(@RequestParam("g") int grade) {
        List<QuestionsResponseDto> questions = questionService.getQuestionsByGrade(grade);
        return new Result(questions);
    }

    @GetMapping("/open")
    public Result getOpenQuestions() {
        List<QuestionsResponseDto> openQuestions = questionService.getOpenQuestions();
        return new Result(openQuestions);
    }

    @GetMapping("/close")
    public Result getCloseQuestions() {
        List<QuestionsResponseDto> closedQuestions = questionService.getCloseQuestions();
        return new Result(closedQuestions);
    }

    @GetMapping("/subject/rank")
    public Result getQuestionsRankBySubject() {
        List<QuestionsRankResponseDto> response = questionService.getQuestionsRankBySubject();
        return new Result(response);
    }
}
