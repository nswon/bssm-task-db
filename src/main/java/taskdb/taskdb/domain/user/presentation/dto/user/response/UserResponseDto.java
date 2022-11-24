package taskdb.taskdb.domain.user.presentation.dto.user.response;

import lombok.Getter;
import taskdb.taskdb.domain.user.domain.User;

@Getter
public class UserResponseDto {
    //TODO
    private final String nickname;
    private final int contributionLevel;

    public UserResponseDto(User user) {
        this.nickname = user.getNickname();
        this.contributionLevel = user.getContributionLevel();
    }
}
