package codestates.preproject.stackoverflow.s3.controller;



import codestates.preproject.stackoverflow.s3.service.ImagesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Positive;
import java.io.IOException;

@RestController
@RequestMapping("/v1/image")
public class ImagesController {

    private final ImagesService imageService;


    public ImagesController(ImagesService imageService) {
        this.imageService = imageService;

    }

    @PostMapping("/{member-id}")
    public ResponseEntity uploadImage(
            @PathVariable("member-id") @Positive long memberId,
            @RequestPart("file") MultipartFile multipartFile
    ) throws IOException {
        return new ResponseEntity<>(imageService.upload(multipartFile,memberId)
                ,HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchImage(
            @PathVariable("member-id") @Positive long memberId,
            @RequestPart("file") MultipartFile multipartFile
    ) throws IOException {
        return new ResponseEntity<>(imageService.update(multipartFile,memberId)
                ,HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteImage(
            @PathVariable("member-id") @Positive long memberId

    ) {
        imageService.remove(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
