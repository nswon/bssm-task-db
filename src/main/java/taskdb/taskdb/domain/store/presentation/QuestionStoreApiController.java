package taskdb.taskdb.domain.store.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taskdb.taskdb.domain.store.presentation.dto.response.StoreQuestionsResponseDto;
import taskdb.taskdb.domain.store.service.QuestionStoreService;
import taskdb.taskdb.global.cover.Result;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class QuestionStoreApiController {
    private final QuestionStoreService questionStoreService;

    @PostMapping("/{id}/new")
    public void create(@PathVariable("id") Long id) {
        questionStoreService.create(id);
    }

    @GetMapping("")
    public Result getQuestions() {
        List<StoreQuestionsResponseDto> savedQuestions = questionStoreService.getQuestions();
        return Result.builder()
                .data(savedQuestions)
                .build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        questionStoreService.delete(id);
    }

    @DeleteMapping("deleteAll")
    public void deleteAll() {
        questionStoreService.deleteAll();
    }
}
