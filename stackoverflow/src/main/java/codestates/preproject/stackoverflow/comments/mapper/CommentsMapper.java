package codestates.preproject.stackoverflow.comments.mapper;

import codestates.preproject.stackoverflow.comments.dto.CommentsDto;
import codestates.preproject.stackoverflow.comments.entity.Comments;
import codestates.preproject.stackoverflow.member.entity.Member;
import codestates.preproject.stackoverflow.post.entity.Posts;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentsMapper {
//    Comments commentsPostDtoToComments(CommentsDto.Post requestBody);
    default Comments commentsPatchDtoToComments(CommentsDto.Patch requestBody){
        Comments comments = new Comments();
        comments.setCommentsid(requestBody.getCommentsid());
        comments.setContent(requestBody.getContent());
        return comments;
    }
    default Comments commentsPostDtoToComments(CommentsDto.Post requestBody){
        Comments comments = new Comments();
        Member member = new Member();
        Posts posts = new Posts();
        member.setMemberid(requestBody.getUserid());
        posts.setPostId(requestBody.getPostid());

        comments.setContent(requestBody.getContents());
        comments.setPosts(posts);
        comments.setMember(member);

        return comments;
    }

    default CommentsDto.Response CommentsToCommentsResponseDto(Comments requestBody){
        CommentsDto.Response response = new CommentsDto.Response();

        response.setMemberid(requestBody.getMember().getMemberid());
        response.setReputation(requestBody.getMember().getReputation());
        response.setCommentsid(requestBody.getCommentsid());
        response.setVotes(requestBody.getVotes());
        response.setContent(requestBody.getContent());

        return response;
    }
}