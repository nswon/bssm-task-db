package taskdb.taskdb.adapter.user.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taskdb.taskdb.application.user.dto.UserJoinRequestDto;
import taskdb.taskdb.application.user.dto.UserUpdateRequestDto;
import taskdb.taskdb.application.user.dto.UserResponseDto;
import taskdb.taskdb.application.user.dto.UsersRankResponseDto;
import taskdb.taskdb.application.user.port.in.GetUserUseCase;
import taskdb.taskdb.application.user.port.in.UserJoinUseCase;
import taskdb.taskdb.application.user.port.in.UserRankUseCase;
import taskdb.taskdb.application.user.port.in.UserUpdateUserCase;
import taskdb.taskdb.infrastructure.support.Result;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserJoinUseCase userJoinUseCase;
    private final GetUserUseCase getUserUseCase;
    private final UserUpdateUserCase userUpdateUserCase;
    private final UserRankUseCase userRankUseCase;

    @PostMapping("/join")
    public void join(@RequestBody UserJoinRequestDto requestDto) {
        userJoinUseCase.join(requestDto);
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable("id") Long id) {
        return getUserUseCase.getUser(id);
    }

    @GetMapping("")
    public UserResponseDto getUser() {
        return getUserUseCase.getUser();
    }

    @PutMapping("/edit")
    public void update(UserUpdateRequestDto requestDto) {
        userUpdateUserCase.update(requestDto);
    }

    @GetMapping("/rank")
    public Result ranking() {
        List<UsersRankResponseDto> response = userRankUseCase.rank();
        return new Result(response);
    }
}
