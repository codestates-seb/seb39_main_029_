package codestates.preproject.stackoverflow.post.mapper;

import codestates.preproject.stackoverflow.member.entity.Member;
import codestates.preproject.stackoverflow.post.dto.PostDto;
import codestates.preproject.stackoverflow.post.entity.PostTag;
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
        System.out.println(requestBody.getPostTag().size());
        System.out.println(requestBody.getPostTag().get(0).getTagId());
        List<PostTag> list =requestBody.getPostTag().stream()
                .map(tag -> {
                    PostTag postsTag = new PostTag();
                    Tags tags = new Tags();
                    tags.setTagsId(tag.getTagId());

                    postsTag.addPost(posts);
                    postsTag.addTags(tags);
                    return postsTag;
                }).collect(Collectors.toList());
        posts.setPostTagsList(list);

        return posts;
    }

    default Posts PatchPostsToPosts(PostDto.Patch requestBody) {
        Posts posts = new Posts();
        posts.setPostId(requestBody.getPostId());
        posts.setSubject(requestBody.getSubject());
        posts.setContent(requestBody.getContent());
        Member member = new Member();
        member.setMemberid(requestBody.getMemberId());
        posts.setMember(member);

        List<PostTag> list =requestBody.getPostTag().stream()
                .map(tag -> {
                    PostTag postsTag = new PostTag();
                    Tags tags = new Tags();
                    tags.setTagsId(tag.getTagId());

                    postsTag.addPost(posts);
                    postsTag.addTags(tags);
                    return postsTag;
                }).collect(Collectors.toList());
        posts.setPostTagsList(list);

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
        post.setPostTag(postTagsResponseDto(posts.getPostTagsList()));
        post.setCommentsList(post.getCommentsList());

        return post;
    }

    List<PostDto.Response> PostsToResponses(List<Posts> posts);

    default List<PostDto.TagsResponse> postTagsResponseDto(List<PostTag> list) {


        List<PostDto.TagsResponse> tags=list.stream()
                .map(postTag -> {
                    PostDto.TagsResponse tagResponse = new PostDto.TagsResponse();
                    tagResponse.setTageId(postTag.getTags().getTagsId());
                    tagResponse.setName(postTag.getTags().getName());

                    return tagResponse;
                }).collect(Collectors.toList());

        return tags;
    }
}
