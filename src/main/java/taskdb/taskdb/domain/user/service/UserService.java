package taskdb.taskdb.domain.user.service;

import taskdb.taskdb.domain.user.dto.UserJoinRequestDto;
import taskdb.taskdb.domain.user.dto.UserProfileRequestDto;
import taskdb.taskdb.domain.user.dto.UserResponseDto;

public interface UserService {
    void join(UserJoinRequestDto requestDto);
    UserResponseDto getUserById(Long id);
    UserResponseDto getUser();
    void update(UserProfileRequestDto requestDto);

}
