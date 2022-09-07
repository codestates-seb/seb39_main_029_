package codestates.preproject.stackoverflow.tags.controller;

import codestates.preproject.stackoverflow.dto.MultiResponseDto;
import codestates.preproject.stackoverflow.tags.dto.TagsDto;
import codestates.preproject.stackoverflow.tags.entity.Tags;
import codestates.preproject.stackoverflow.tags.mapper.TagsMapper;
import codestates.preproject.stackoverflow.tags.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/tags")
@Validated
@Slf4j
//@CrossOrigin(originPatterns = {"https://localhost:3000","https://localhost:3001","http://localhost:3000","http://localhost:3001",
//        "http://localhost:3000/","http://localhost:3001/"},
//        allowedHeaders = {"POST","GET","PATCH","DELETE"})
public class TagsController {

    private final TagService tagService;

    private final TagsMapper mapper;

    public TagsController(TagService tagService, TagsMapper mapper) {
        this.tagService = tagService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postTags(@Valid @RequestBody TagsDto.Post requestBody) {

        Tags tag = tagService.createTags(mapper.TagPostDtoToTag(requestBody));

        return new ResponseEntity(mapper.tagToTagResponse(tag), HttpStatus.CREATED);
    }

    //존재하는 태그 목록을 보여주는 API
    @GetMapping("/list")
    public ResponseEntity getTagsList() {
        List<Tags> tags = tagService.findTagsList();

        return new ResponseEntity(mapper.tagsToTagResponses(tags), HttpStatus.OK);
    }

    //Tag 페이지에서 tag 전체 목록을 보여주는 API
    @GetMapping
    public ResponseEntity getTags(
            @Positive @RequestParam int page,
            @Positive @RequestParam int size,
            @RequestParam String arrange
                                  ) {
        Page<Tags> pageTags = tagService.findTags(page-1, size, arrange);
        List<TagsDto.TagsCountResponse> tags = tagService.changeTagPage(pageTags.getContent());

        return new ResponseEntity(
                new MultiResponseDto<>(tags,pageTags), HttpStatus.OK);
    }



}
