package taskdb.taskdb.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ImageResponse {
    private String imgPath;
    private String imgUrl;

    public ImageResponse() {
    }

    @Builder
    public ImageResponse(String imgPath, String imgUrl) {
        this.imgPath = imgPath;
        this.imgUrl = imgUrl;
    }
}
