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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionApiController {
    private final QuestionService questionService;

    @PostMapping("/new")
    public void create(QuestionCreateRequestDto requestDto) {
        questionService.create(requestDto);
    }

    @GetMapping("")
    public Result getQuestions() {
        return Result.builder()
                .data(questionService.getQuestions())
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
    public List<QuestionsResponseDto> searchByTitleOrId(@RequestParam("q") Object keyword) {
        return questionService.searchByTitleOrId(keyword);
    }

    @GetMapping("/categories")
    public List<QuestionsResponseDto> getQuestionsByCategory(@RequestParam("c")Category category) {
        return questionService.getQuestionsByCategory(category);
    }

    @GetMapping("/grade")
    public List<QuestionsResponseDto> getQuestionsByGrade(@RequestParam("g") int grade) {
        return questionService.getQuestionsByGrade(grade);
    }

    @GetMapping("/open")
    public List<QuestionsResponseDto> getOpenQuestions() {
        return questionService.getOpenQuestions();
    }

    @GetMapping("/close")
    public List<QuestionsResponseDto> getCloseQuestions() {
        return questionService.getCloseQuestions();
    }
}
