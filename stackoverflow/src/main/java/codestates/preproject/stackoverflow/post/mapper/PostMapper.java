package codestates.preproject.stackoverflow.post.mapper;

import codestates.preproject.stackoverflow.member.entity.Member;
import codestates.preproject.stackoverflow.post.dto.PostDto;
import codestates.preproject.stackoverflow.post.entity.Posts;
import codestates.preproject.stackoverflow.post.tags.TagMap;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    default Posts makePostsToPosts(PostDto.Post requestBody){
        Posts posts = new Posts();
        posts.setSubject(requestBody.getSubject());
        posts.setContent(requestBody.getContent());
//        posts.setTag(TagMap.sendTag(requestBody.getTag()));

//        Member member = new Member();
//        member.setId(requestBody.getMemberId());
//
//        posts.setMember(member);

        return posts;
    }

    Posts PatchPostsToPosts(PostDto.Patch requestBody);

    PostDto.Response PostsToResponse(Posts posts);

    List<PostDto.Response> PostsToResponses(List<Posts> posts);
}
