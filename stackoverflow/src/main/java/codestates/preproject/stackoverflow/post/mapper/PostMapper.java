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
        posts.setCommentsCount(0);

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
        post.setVotes(posts.getVotes());
        post.setMemberId(posts.getMember().getMemberid());
        post.setCreateAt(posts.getCreatedAt());
        post.setPostTag(postTagsResponseDto(posts.getPostTagsList()));
        post.setCommentsList(post.getCommentsList());
        post.setCommentsCount(posts.getCommentsCount());

        post.setNickName(posts.getMember().getNickName());
        post.setReputation(posts.getMember().getReputation());

        return post;
    }

    List<PostDto.Response> PostsToResponses(List<Posts> posts);

    default List<PostDto.TagsResponse> postTagsResponseDto(List<PostTag> list) {

        List<PostDto.TagsResponse> tags=list.stream()
                .map(postTag -> {
                    PostDto.TagsResponse tagResponse = new PostDto.TagsResponse();
                    tagResponse.setTagId(postTag.getTags().getTagsId());
                    tagResponse.setName(postTag.getTags().getName());

                    return tagResponse;
                }).collect(Collectors.toList());

        return tags;
    }

    //상수가 추가한 코드 입니다.
    // 태그 수정
    default List<PostDto.uResponse> PostsToPostuResponseDto(List<Posts> requestBody){
        return requestBody.stream().map(posts -> {
            PostDto.uResponse response = new PostDto.uResponse();
            response.setPostId(posts.getPostId());
            response.setCreateAt(posts.getCreatedAt());

            response.setVote(posts.getVotes());

            response.setCommentsCount(posts.getCommentsCount());
            response.setSubject(posts.getSubject());
            response.setTag(postTagsResponseDto(posts.getPostTagsList()));
            return response;
        }).collect(Collectors.toList());
    }

    default PostDto.voteResponse PostsToVoteResponse(Posts posts) {
        PostDto.voteResponse post = new PostDto.voteResponse();
        post.setPostId(posts.getPostId());
        post.setSubject(posts.getSubject());
        post.setMemberId(posts.getMember().getMemberid());
        post.setVotes(posts.getVotes());
        post.setMemberId(posts.getMember().getMemberid());
        post.setCreateAt(posts.getCreatedAt());
        post.setPostTag(postTagsResponseDto(posts.getPostTagsList()));
        post.setCommentsList(post.getCommentsList());
        post.setCommentsCount(posts.getCommentsCount());
        post.setReputation(posts.getMember().getReputation());
        post.setIsvote(posts.getIsVote());

        return post;
    }
}
