package taskdb.taskdb.domain.user.presentation.dto.user.request;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class UserProfileRequestDto {
    private MultipartFile file;
    private String nickname;
}
