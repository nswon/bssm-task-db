package taskdb.taskdb.domain.user.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taskdb.taskdb.domain.user.presentation.dto.request.UserJoinRequestDto;
import taskdb.taskdb.domain.user.presentation.dto.request.UserProfileRequestDto;
import taskdb.taskdb.domain.user.presentation.dto.response.UserResponseDto;
import taskdb.taskdb.domain.user.presentation.dto.response.UsersRankResponseDto;
import taskdb.taskdb.domain.user.service.UserService;
import taskdb.taskdb.global.support.Result;

import javax.validation.Valid;
import java.io.IOException;
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

    @PutMapping("/edit")
    public void updateProfileImage(UserProfileRequestDto requestDto) throws IOException {
        userService.updateProfile(requestDto);
    }

    @GetMapping("/rank")
    public Result rank() {
        List<UsersRankResponseDto> usersRank = userService.rank();
        return Result.builder()
                .data(usersRank)
                .build();
    }
}
