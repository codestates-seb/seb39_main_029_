package codestates.preproject.stackoverflow.comments.mapper;

import codestates.preproject.stackoverflow.comments.dto.CommentsDto;
import codestates.preproject.stackoverflow.comments.entity.Comments;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentsMapper {
    Comments commentsPostDtoToComments(CommentsDto.Post requestBody);
}
