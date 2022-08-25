package codestates.preproject.stackoverflow.post.mapper;

import codestates.preproject.stackoverflow.member.entity.Member;
import codestates.preproject.stackoverflow.post.dto.PostDto;
import codestates.preproject.stackoverflow.post.entity.Posts;
import codestates.preproject.stackoverflow.tags.Tags;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    default Posts makePostsToPosts(PostDto.Post requestBody){
        Posts posts = new Posts();

        Member member = new Member();
        member.setMemberid(requestBody.getMemberId());

        posts.setMember(member);

        posts.setSubject(requestBody.getSubject());
        posts.setContent(requestBody.getContent());

        posts.setTag(requestBody.getTag());

        return posts;
    }

    default Posts PatchPostsToPosts(PostDto.Patch requestBody) {
        Posts posts = new Posts();
        posts.setPostId(requestBody.getPostId());
        posts.setSubject(requestBody.getSubject());
        posts.setContent(requestBody.getContent());

        posts.setTag(requestBody.getTag());

        Member member = new Member();
        member.setMemberid(requestBody.getMemberId());

        posts.setMember(member);

        return posts;
    }

    default PostDto.Response PostsToResponse(Posts posts) {
        PostDto.Response post = new PostDto.Response();
        post.setPostId(posts.getPostId());
        post.setSubject(posts.getSubject());
        post.setContent(posts.getContent());
        post.setVote(posts.getVotes());
        post.setMemberId(posts.getMember().getMemberid());
        post.setCreateAt(posts.getCreatedAt());
        post.setTag(posts.getTag());

        return post;
    }

    List<PostDto.Response> PostsToResponses(List<Posts> posts);
}
