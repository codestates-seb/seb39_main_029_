package codestates.preproject.stackoverflow.tags.mapper;

import codestates.preproject.stackoverflow.tags.Repository.TagRepository;
import codestates.preproject.stackoverflow.tags.dto.TagsDto;
import codestates.preproject.stackoverflow.tags.entity.Tags;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TagsMapper {
    Tags TagPostDtoToTag(TagsDto.Post requestBody) ;

    TagsDto.Response tagToTagResponse(Tags tags);

    List<TagsDto.Response> tagsToTagResponses(List<Tags> tags);
}
