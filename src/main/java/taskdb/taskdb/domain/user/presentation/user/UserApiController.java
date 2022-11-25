package taskdb.taskdb.domain.user.presentation.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taskdb.taskdb.domain.questions.presentation.dto.response.QuestionsResponseDto;
import taskdb.taskdb.domain.store.presentation.dto.response.StoreQuestionsResponseDto;
import taskdb.taskdb.domain.user.presentation.dto.user.request.UserJoinRequestDto;
import taskdb.taskdb.domain.user.presentation.dto.user.response.UserResponseDto;
import taskdb.taskdb.domain.user.service.user.UserService;
import taskdb.taskdb.global.cover.Result;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserApiController {
    private final UserService userService;

    @PostMapping("/join")
    public boolean join(@RequestBody @Valid UserJoinRequestDto requestDto) {
        return userService.join(requestDto);
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/questions")
    public Result getQuestions() {
        List<QuestionsResponseDto> response = userService.getQuestions();
        return Result.builder()
                .data(response)
                .build();
    }

    @GetMapping("/saved/questions")
    public Result getSavedQuestions() {
        List<StoreQuestionsResponseDto> response = userService.getSavedQuestions();
        return Result.builder()
                .data(response)
                .build();
    }
}
