package taskdb.taskdb.domain.user.presentation.dto.request;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class UserProfileRequestDto {
    private MultipartFile file;
    private String nickname;

    public UserProfileRequestDto() {
    }

    public UserProfileRequestDto(MultipartFile file, String nickname) {
        this.file = file;
        this.nickname = nickname;
    }
}
