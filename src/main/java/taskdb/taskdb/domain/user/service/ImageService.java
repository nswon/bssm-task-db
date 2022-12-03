package taskdb.taskdb.domain.user.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import taskdb.taskdb.domain.user.domain.Image;
import taskdb.taskdb.domain.user.exception.UploadFailedException;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    private static final String SEPARATION = "_";
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3 amazonS3;
    private final AmazonS3Client amazonS3Client;

    public Image create(MultipartFile file) {
        String imgPath = UUID.randomUUID() + SEPARATION + file.getOriginalFilename();
        upload(file, imgPath);
        String url = String.valueOf(amazonS3Client.getUrl(bucket, imgPath));
        return Image.of(imgPath, url);
    }

    private void upload(MultipartFile file, String imgPath) {
        try {
            uploadS3(file, imgPath);
        } catch (IOException e) {
            throw new UploadFailedException();
        }
    }

    private void uploadS3(MultipartFile multipartFile, String imgPath) throws IOException {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());
        amazonS3.putObject(new PutObjectRequest(bucket, imgPath, multipartFile.getInputStream(), objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public void delete(String imgPath) {
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, imgPath));
    }
}

