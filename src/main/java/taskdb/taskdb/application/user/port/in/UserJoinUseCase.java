package taskdb.taskdb.application.user.port.in;

import taskdb.taskdb.application.user.dto.UserJoinRequestDto;

public interface UserJoinUseCase {
    void join(UserJoinRequestDto requestDto);
}
