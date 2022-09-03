package codestates.preproject.stackoverflow.s3.upload;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class S3Upload {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.dir}")
    private String dir;

    private final AmazonS3Client s3Client;

   /* public String upload(InputStream inputStream, String originFileName, String fileSize) {
        String s3FileName = UUID.randomUUID() + "-" + originFileName;

        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentLength(Long.parseLong(fileSize));

        s3Client.putObject(bucket, s3FileName, inputStream, objMeta);

        return s3Client.getUrl(bucket, dir + s3FileName).toString();
    }*/


    public String upload(MultipartFile multipartFile) throws IOException {
        String s3FileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentType(multipartFile.getContentType());
        s3Client.putObject(bucket, s3FileName, multipartFile.getInputStream(), objMeta);

        return s3Client.getUrl(bucket, dir + s3FileName).toString();
    }
}
