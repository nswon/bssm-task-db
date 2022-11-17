package taskdb.taskdb.domain.user.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taskdb.taskdb.domain.user.presentation.dto.request.UserJoinRequestDto;
import taskdb.taskdb.domain.user.service.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserApiController {
    private final UserService userService;

    @PostMapping("/join")
    public boolean join(@RequestBody @Valid UserJoinRequestDto requestDto) {
        return userService.join(requestDto);
    }
}
