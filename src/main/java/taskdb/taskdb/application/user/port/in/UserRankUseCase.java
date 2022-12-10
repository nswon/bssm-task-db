package taskdb.taskdb.application.user.port.in;

import taskdb.taskdb.application.user.dto.UsersRankResponseDto;

import java.util.List;

public interface UserRankUseCase {
    List<UsersRankResponseDto> rank();
}
