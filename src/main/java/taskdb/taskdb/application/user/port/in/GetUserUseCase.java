package taskdb.taskdb.application.user.port.in;

import taskdb.taskdb.application.user.dto.UserResponseDto;

public interface GetUserUseCase {
    UserResponseDto getUser(Long id);
    UserResponseDto getUser();
}
