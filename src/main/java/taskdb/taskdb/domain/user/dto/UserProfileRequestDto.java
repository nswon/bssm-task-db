package taskdb.taskdb.domain.user.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class UserProfileRequestDto {
    private MultipartFile file;
    private String nickname;
    private String bio;

    public UserProfileRequestDto() {
    }

    public UserProfileRequestDto(MultipartFile file, String nickname, String bio) {
        this.file = file;
        this.nickname = nickname;
        this.bio = bio;
    }
}
