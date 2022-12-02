package taskdb.taskdb.global.s3;

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
