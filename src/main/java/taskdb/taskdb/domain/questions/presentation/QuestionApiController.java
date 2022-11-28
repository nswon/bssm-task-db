package taskdb.taskdb.domain.questions.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taskdb.taskdb.domain.questions.domain.Category;
import taskdb.taskdb.domain.questions.presentation.dto.request.QuestionCreateRequestDto;
import taskdb.taskdb.domain.questions.presentation.dto.request.QuestionUpdateRequestDto;
import taskdb.taskdb.domain.questions.presentation.dto.response.QuestionResponseDto;
import taskdb.taskdb.domain.questions.presentation.dto.response.QuestionsResponseDto;
import taskdb.taskdb.domain.questions.service.QuestionService;
import taskdb.taskdb.global.cover.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public Result getQuestionsByCategory(@RequestParam("c")Category category) {
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

    @GetMapping("/recommend")
    public Result getRecommendTitles(@RequestParam("q") String keyword) {
        List<String> recommendTitles = questionService.getRecommendTitles(keyword);
        return Result.builder()
                .data(recommendTitles)
                .build();
    }
}
