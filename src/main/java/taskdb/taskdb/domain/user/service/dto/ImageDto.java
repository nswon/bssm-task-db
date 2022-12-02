package taskdb.taskdb.domain.user.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ImageDto {
    private final String imgPath;
    private final String imgUrl;

    @Builder
    public ImageDto(String imgPath, String imgUrl) {
        this.imgPath = imgPath;
        this.imgUrl = imgUrl;
    }
}
