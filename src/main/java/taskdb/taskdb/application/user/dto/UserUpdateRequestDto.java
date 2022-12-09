package taskdb.taskdb.application.user.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class UserUpdateRequestDto {
    private MultipartFile file;
    private String nickname;
    private String bio;

    public UserUpdateRequestDto() {
    }

    public UserUpdateRequestDto(MultipartFile file, String nickname, String bio) {
        this.file = file;
        this.nickname = nickname;
        this.bio = bio;
    }
}
