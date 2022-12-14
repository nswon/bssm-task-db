package taskdb.taskdb;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import taskdb.taskdb.application.user.service.ImageService;
import taskdb.taskdb.domain.user.entity.Image;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final ImageService imageService;

    @PostMapping("/test")
    public void test(@RequestPart MultipartFile file) {
        Image image = imageService.create(file);
        System.out.println("path : " + image.getPath());
        System.out.println("url : " + image.getUrl());
    }
}
