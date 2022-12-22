package taskdb.taskdb.adapter.store.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taskdb.taskdb.application.store.dto.QuestionStoresResponseDto;
import taskdb.taskdb.application.store.port.in.DeleteQuestionStoreUseCase;
import taskdb.taskdb.application.store.port.in.GetQuestionStoreUseCase;
import taskdb.taskdb.application.store.port.in.QuestionStoreSaveUseCase;
import taskdb.taskdb.common.support.Result;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class QuestionStoreController {
    private final QuestionStoreSaveUseCase questionStoreSaveUseCase;
    private final GetQuestionStoreUseCase getQuestionStoreUseCase;
    private final DeleteQuestionStoreUseCase deleteQuestionStoreUseCase;

    @PostMapping("/{id}/new")
    public void create(@PathVariable("id") Long id) {
        questionStoreSaveUseCase.save(id);
    }

    @GetMapping("")
    public Result getQuestions() {
        QuestionStoresResponseDto savedQuestions = getQuestionStoreUseCase.getQuestions();
        return new Result(savedQuestions);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        deleteQuestionStoreUseCase.delete(id);
    }

    @DeleteMapping("deleteAll")
    public void deleteAll() {
        deleteQuestionStoreUseCase.delete();
    }
}
