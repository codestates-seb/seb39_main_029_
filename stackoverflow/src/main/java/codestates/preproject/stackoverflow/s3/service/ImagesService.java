package codestates.preproject.stackoverflow.s3.service;

import codestates.preproject.stackoverflow.exception.BusinessLogicException;
import codestates.preproject.stackoverflow.exception.ExceptionCode;
import codestates.preproject.stackoverflow.member.entity.Member;
import codestates.preproject.stackoverflow.member.service.MemberService;

import codestates.preproject.stackoverflow.s3.entity.Images;
import codestates.preproject.stackoverflow.s3.repository.ImagesRepository;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ImagesService {

    private final AmazonS3 amazonS3;
    private final ImagesRepository repository;

    private final MemberService memberService;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.dir}")
    private String dirName;

    public ImagesService(AmazonS3 amazonS3, ImagesRepository repository, MemberService memberService) {
        this.amazonS3 = amazonS3;
        this.repository = repository;
        this.memberService = memberService;
    }

    public Images upload(MultipartFile multipartFile, long memberId) throws IOException {
        Member member = memberService.findVerifiedMember(memberId);
        if (member.getImage() != null) {
            throw new BusinessLogicException(ExceptionCode.IMAGE_EXISTS);
        }

        Optional<File> file = Optional.ofNullable(convertMultipartFileToFile(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("error: MultipartFile -> File convert fail")));
        Images image = upload(file.get());
        image.setMember(member);
        repository.save(image);

        member.addImage(image);

        return image;
    }

    private Images upload(File file) {
        String key = randomFileName(file);
        putS3(file, key);
        removeFile(file);
        Images image = new Images();
        image.setImagesKey(key);
        return image;

    }

    private String randomFileName(File file) {
        return dirName + "/" + UUID.randomUUID() + file.getName();
    }

    private String putS3(File uploadFile, String fileName) {
        amazonS3.putObject(new PutObjectRequest(bucket, fileName, uploadFile)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return getS3(bucket, fileName);
    }

    private String getS3(String bucket, String fileName) {
        return amazonS3.getUrl(bucket, fileName).toString();
    }

    private void removeFile(File file) {
        file.delete();
    }

    public Optional<File> convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        System.out.println(file.getName());
        if (file.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(multipartFile.getBytes());
            }
            return Optional.of(file);
        }
        return Optional.empty();
    }

    public void remove(long memberId) {
        Member member = memberService.findVerifiedMember(memberId);
        if (member.getImage() == null) {
            throw new BusinessLogicException(ExceptionCode.IMAGE_NOT_FOUND);
        }

        String key = member.getImage().getImagesKey();

        if (!amazonS3.doesObjectExist(bucket, key)) {
            throw new AmazonS3Exception("Object " + key + " does not exist!");
        }
        amazonS3.deleteObject(bucket, key);
        repository.delete(member.getImage());
        member.setImage(null);
    }

    public Images update(MultipartFile multipartFile, long memberId) throws IOException {
        Member member = memberService.findVerifiedMember(memberId);
        if (multipartFile == null) {
            throw new BusinessLogicException(ExceptionCode.IMAGE_NOT_FOUND);
        }
        if (member.getImage() == null) {
            return upload(multipartFile, memberId);
        } else {
            remove(memberId);
            return upload(multipartFile, memberId);
        }
    }


}