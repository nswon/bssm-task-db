package taskdb.taskdb.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taskdb.taskdb.domain.user.dto.UserJoinRequestDto;
import taskdb.taskdb.domain.user.dto.UserProfileRequestDto;
import taskdb.taskdb.domain.user.dto.UserResponseDto;
import taskdb.taskdb.domain.user.dto.UsersRankResponseDto;
import taskdb.taskdb.domain.user.service.RankingService;
import taskdb.taskdb.domain.user.service.UserService;
import taskdb.taskdb.domain.user.service.UserServiceImpl;
import taskdb.taskdb.global.support.Result;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserApiController {
    private final UserService userService;
    private final RankingService rankingService;

    @PostMapping("/join")
    public void join(@RequestBody UserJoinRequestDto requestDto) {
        userService.join(requestDto);
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("")
    public UserResponseDto getUser() {
        return userService.getUser();
    }

    @PutMapping("/edit")
    public void update(UserProfileRequestDto requestDto) {
        userService.update(requestDto);
    }

    @GetMapping("/rank")
    public Result rank() {
        List<UsersRankResponseDto> response = rankingService.getRankingList();
        return new Result(response);
    }
}
