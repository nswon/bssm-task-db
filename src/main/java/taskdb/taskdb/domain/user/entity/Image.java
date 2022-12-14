package taskdb.taskdb.domain.user.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Image {
    private static final String DEFAULT_IMAGE_PATH = "696c3fa1-a548-49bd-b2be-1fd5761365e7_ho.png";
    private static final String DEFAULT_IMAGE_URL = "https://taskdb-new.s3.ap-northeast-2.amazonaws.com/696c3fa1-a548-49bd-b2be-1fd5761365e7_ho.png";

    @Column(name = "img_path")
    private String path;

    @Column(name = "img_url")
    private String url;

    protected Image() {
    }

    private Image(String path, String url) {
        this.path = path;
        this.url = url;
    }

    public static Image of(String path, String url) {
        return new Image(path, url);
    }

    public static Image createDefault() {
        return new Image(DEFAULT_IMAGE_PATH, DEFAULT_IMAGE_URL);
    }

    public boolean isDefault() {
        return path.equals(DEFAULT_IMAGE_PATH);
    }

    public boolean isEmpty() {
        return path.isEmpty();
    }
}
