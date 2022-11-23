package taskdb.taskdb.domain.questions.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taskdb.taskdb.domain.questions.presentation.dto.request.QuestionCreateRequestDto;
import taskdb.taskdb.domain.questions.presentation.dto.response.QuestionsResponseDto;
import taskdb.taskdb.domain.questions.service.QuestionService;

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
    public List<QuestionsResponseDto> getQuestions() {
        return questionService.getQuestions();
    }
}
