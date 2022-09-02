package codestates.preproject.stackoverflow.member.mapper;

import codestates.preproject.stackoverflow.member.dto.MemberDto;
import codestates.preproject.stackoverflow.member.entity.Member;
import codestates.preproject.stackoverflow.post.dto.PostDto;
import codestates.preproject.stackoverflow.post.entity.Posts;
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
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member memberJoinToMember(MemberDto.Join requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Member member = new Member();

        member.setNickName( requestBody.getNickName() );
        member.setPassword( requestBody.getPassword() );
        member.setEmail( requestBody.getEmail() );

        return member;
    }

    @Override
    public Member memberLoginToMember(MemberDto.Login requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Member member = new Member();

        member.setMemberid( requestBody.getMemberid() );
        member.setPassword( requestBody.getPassword() );
        member.setEmail( requestBody.getEmail() );

        return member;
    }

    @Override
    public Member memberPatchToMember(MemberDto.Patch requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Member member = new Member();

        member.setMemberid( requestBody.getMemberid() );
        member.setNickName( requestBody.getNickName() );
        member.setPassword( requestBody.getPassword() );
        member.setSelfId( requestBody.getSelfId() );
        member.setLocation( requestBody.getLocation() );
        member.setTitle( requestBody.getTitle() );

        return member;
    }

    @Override
    public MemberDto.Response memberToMemberResponseDto(Member requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        long memberid = 0L;
        String nickName = null;
        String selfId = null;
        String location = null;
        String title = null;
        Integer reputation = null;
        List<PostDto.uResponse> postsList = null;

        memberid = requestBody.getMemberid();
        nickName = requestBody.getNickName();
        selfId = requestBody.getSelfId();
        location = requestBody.getLocation();
        title = requestBody.getTitle();
        reputation = requestBody.getReputation();
        postsList = postsListTouResponseList( requestBody.getPostsList() );

        String imageUrl = null;

        MemberDto.Response response = new MemberDto.Response( memberid, nickName, selfId, location, title, reputation, postsList, imageUrl );

        return response;
    }

    protected PostDto.uResponse postsTouResponse(Posts posts) {
        if ( posts == null ) {
            return null;
        }

        PostDto.uResponse uResponse = new PostDto.uResponse();

        uResponse.setPostId( posts.getPostId() );
        uResponse.setSubject( posts.getSubject() );
        uResponse.setCommentsCount( posts.getCommentsCount() );

        return uResponse;
    }

    protected List<PostDto.uResponse> postsListTouResponseList(List<Posts> list) {
        if ( list == null ) {
            return null;
        }

        List<PostDto.uResponse> list1 = new ArrayList<PostDto.uResponse>( list.size() );
        for ( Posts posts : list ) {
            list1.add( postsTouResponse( posts ) );
        }

        return list1;
    }
}
