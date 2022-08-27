package codestates.preproject.stackoverflow.tags.service;

import codestates.preproject.stackoverflow.exception.BusinessLogicException;
import codestates.preproject.stackoverflow.exception.ExceptionCode;
import codestates.preproject.stackoverflow.post.dto.PostDto;
import codestates.preproject.stackoverflow.post.entity.Posts;
import codestates.preproject.stackoverflow.post.service.PostService;
import codestates.preproject.stackoverflow.tags.Repository.TagRepository;
import codestates.preproject.stackoverflow.tags.dto.TagsDto;
import codestates.preproject.stackoverflow.tags.entity.Tags;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class TagService {
    private final TagRepository repository;

    public TagService(TagRepository repository) {
        this.repository = repository;
    }

    public Tags createTags(Tags tags) {
        return repository.save(tags);
    }

    public List<Tags> findTagsList() {
        return repository.findAll();
    }

    public Page<Tags> findTags(int page, int size, String arrange) {
        return repository.findAll(PageRequest.of(page, size,
                Sort.by(arrange).ascending()));
    }

    //일반 Tags 리스트를 Tag 페이지에 보여줄 수 있도록 count 변수 추가
    public List<TagsDto.TagsCountResponse> changeTagPage(List<Tags> tags) {
        List<TagsDto.TagsCountResponse> responses = tags.stream()
                .map(tag -> {
                    TagsDto.TagsCountResponse response = new TagsDto.TagsCountResponse();
                    response.setTagsId(tag.getTagsId());
                    response.setName(tag.getName());
                    response.setCount((long) tag.getPostTags().size());
                    return response;
                }).collect(Collectors.toList());

        /*Collections.sort(responses, new Comparator<TagsDto.TagsCountResponse>() {
            @Override
            public int compare(TagsDto.TagsCountResponse o1, TagsDto.TagsCountResponse o2) {
                return (int) (o1.getTagsId() - o2.getTagsId());
            }
        });*/
        return responses;
    }


    public Tags findVerifiedTags(long tagsId) {
        Optional<Tags> optionalTag =
                repository.findById(tagsId);

        Tags findTags =
                optionalTag.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.TAGS_NOT_FOUND));

        return findTags;
    }
}
