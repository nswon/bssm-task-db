package taskdb.taskdb.application.user.port.in;

import taskdb.taskdb.application.user.dto.UserUpdateRequestDto;

public interface UserUpdateUserCase {
    void update(UserUpdateRequestDto requestDto);
}
