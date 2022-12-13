package taskdb.taskdb.application.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
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
