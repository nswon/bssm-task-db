package taskdb.taskdb.domain.questions.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taskdb.taskdb.domain.questions.presentation.dto.request.QuestionCreateRequestDto;
import taskdb.taskdb.domain.questions.presentation.dto.response.QuestionResponseDto;
import taskdb.taskdb.domain.questions.service.QuestionService;
import taskdb.taskdb.global.cover.Result;

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
}
