package codestates.preproject.stackoverflow.post.mapper;

import codestates.preproject.stackoverflow.member.entity.Member;
import codestates.preproject.stackoverflow.post.dto.PostDto;
import codestates.preproject.stackoverflow.post.entity.Posts;
import codestates.preproject.stackoverflow.tags.entity.Tags;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PostMapper {

    default Posts makePostsToPosts(PostDto.Post requestBody){
        Posts posts = new Posts();

        Member member = new Member();
        member.setMemberid(requestBody.getMemberId());

        posts.setMember(member);

        posts.setSubject(requestBody.getSubject());
        posts.setContent(requestBody.getContent());

        List<Tags> list =requestBody.getTag().stream()
                .map(tag -> {
                    Tags tags = new Tags();
                    tags.addPosts(posts);
                    tags.setData(tag.getData());
                    return tags;
                }).collect(Collectors.toList());
        posts.setTag(list);

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
        post.setTag(TagsResponseDto(posts.getTag()));

        post.setCommentsList(post.getCommentsList());
        return post;
    }

    List<PostDto.Response> PostsToResponses(List<Posts> posts);

    default List<PostDto.TagsResponse> TagsResponseDto(List<Tags> list) {


        List<PostDto.TagsResponse> tags=list.stream()
                .map(tag -> {
                    PostDto.TagsResponse tagResponse = new PostDto.TagsResponse();
                    tagResponse.setTageId(tag.getTagsId());
                    tagResponse.setData(tag.getData());
                    tagResponse.setPostsId(tag.getPosts().getPostId());
                    return tagResponse;
                }).collect(Collectors.toList());

        return tags;
    }
}
