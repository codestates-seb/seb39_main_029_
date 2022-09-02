package codestates.preproject.stackoverflow.tags.mapper;

import codestates.preproject.stackoverflow.tags.dto.TagsDto;
import codestates.preproject.stackoverflow.tags.entity.Tags;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-02T23:31:21+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.16.1 (Azul Systems, Inc.)"
)
@Component
public class TagsMapperImpl implements TagsMapper {

    @Override
    public Tags TagPostDtoToTag(TagsDto.Post requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Tags tags = new Tags();

        tags.setName( requestBody.getName() );
        tags.setContent( requestBody.getContent() );

        return tags;
    }

    @Override
    public TagsDto.Response tagToTagResponse(Tags tags) {
        if ( tags == null ) {
            return null;
        }

        TagsDto.Response response = new TagsDto.Response();

        if ( tags.getTagsId() != null ) {
            response.setTagsId( tags.getTagsId() );
        }
        response.setName( tags.getName() );

        return response;
    }

    @Override
    public List<TagsDto.Response> tagsToTagResponses(List<Tags> tags) {
        if ( tags == null ) {
            return null;
        }

        List<TagsDto.Response> list = new ArrayList<TagsDto.Response>( tags.size() );
        for ( Tags tags1 : tags ) {
            list.add( tagToTagResponse( tags1 ) );
        }

        return list;
    }
}
