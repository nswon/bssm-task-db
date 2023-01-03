package taskdb.taskdb.application.user.port.in;

import taskdb.taskdb.application.user.dto.UserResponseDto;

import java.util.UUID;

public interface GetUserUseCase {
    UserResponseDto getUser(UUID id);
    UserResponseDto getUser();
}
