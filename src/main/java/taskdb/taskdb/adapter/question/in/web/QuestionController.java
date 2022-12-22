package taskdb.taskdb.adapter.question.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taskdb.taskdb.application.question.dto.*;
import taskdb.taskdb.application.question.port.in.*;
import taskdb.taskdb.infrastructure.support.Result;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private final QuestionSaveUseCase questionSaveUseCase;
    private final QuestionGetUseCase questionGetUseCase;
    private final QuestionUpdateUseCase questionUpdateUseCase;
    private final QuestionDeleteUseCase questionDeleteUserCase;
    private final QuestionRankUseCase questionRankUseCase;

    @PostMapping("/new")
    public void create(@RequestBody @Valid QuestionCreateRequestDto requestDto) {
        questionSaveUseCase.save(requestDto);
    }

    @GetMapping("")
    public Result getQuestions() {
        List<QuestionAllResponseDto> response = questionGetUseCase.getQuestions();
        return new Result(response);
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
        List<QuestionAllResponseDto> questions = questionGetUseCase.getQuestionsByKeyword(keyword);
        return new Result(questions);
    }

    @GetMapping("/categories")
    public Result getQuestionsByCategory(@RequestParam("c") String command) {
        List<QuestionAllResponseDto> questions = questionGetUseCase.getQuestionsByCategory(command);
        return new Result(questions);
    }

    @GetMapping("/grade")
    public Result getQuestionsByGrade(@RequestParam("g") int grade) {
        List<QuestionAllResponseDto> questions = questionGetUseCase.getQuestionsByGrade(grade);
        return new Result(questions);
    }

    @GetMapping("/status")
    public Result getQuestionsByStatus(@RequestParam("status") String command) {
        List<QuestionAllResponseDto> openQuestions = questionGetUseCase.getQuestionsByStatus(command);
        return new Result(openQuestions);
    }

    @GetMapping("/rank")
    public Result getQuestionsRank() {
        List<QuestionsRankResponseDto> response = questionRankUseCase.rank();
        return new Result(response);
    }
}
