package codestates.preproject.stackoverflow.member.controller;

import codestates.preproject.stackoverflow.dto.SingleResponseDto;
import codestates.preproject.stackoverflow.member.dto.MemberDto;


import codestates.preproject.stackoverflow.member.entity.Member;
import codestates.preproject.stackoverflow.member.mapper.MemberMapper;
import codestates.preproject.stackoverflow.member.service.MemberService;


import codestates.preproject.stackoverflow.post.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Positive;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/members")
@Validated
@Slf4j
//@CrossOrigin(originPatterns = {"https://localhost:3000","https://localhost:3001","http://localhost:3000","http://localhost:3001",
//        "http://localhost:3000/","http://localhost:3001/"},
//        allowedHeaders = {"POST","GET","PATCH","DELETE"})
public class MemberController {
    private final MemberService memberService;

    private final MemberMapper memberMapper;

    private final PostService postService;

    public MemberController(MemberService memberService, MemberMapper memberMapper, PostService postService){
        this.memberMapper = memberMapper;
        this.memberService = memberService;
        this.postService = postService;
    }

    @PostMapping("/join")
    public ResponseEntity joinMember(@Valid @RequestBody MemberDto.Join join){
        Member member = memberMapper.memberJoinToMember(join);
        memberService.createMember(member);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @PatchMapping("/update/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") @Positive long memberid,
                                      @RequestBody MemberDto.Patch patch){
        patch.setMemberid(memberid);
        Member member = memberMapper.memberPatchToMember(patch);
        Member updateMember = memberService.updateMember(member);
        MemberDto.Response result = memberMapper.memberToMemberResponseDto(updateMember);

        return new ResponseEntity(result,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") @Positive long memberid){
        memberService.deleteMember(memberid);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    @GetMapping("/myPage/{member-id}")
    public ResponseEntity showMember(@PathVariable("member-id") long memberid, @RequestParam int page, @RequestParam int size){
        Member member = memberService.findVerifiedMember(memberid);
        MemberDto.Response response = memberMapper.memberToMemberResponseDto(member);
        response.setPostsList(postService.findUserPosts(memberid, page, size));
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> loginMember(@Valid @RequestBody MemberDto.Refresh refresh, HttpServletResponse response){
        String result = memberService.refresh(refresh, response);
        response.addHeader("Authorization",result);
        response.addHeader("Memberid", String.valueOf(refresh.getMemberid()));
        return new ResponseEntity(HttpStatus.OK);
    }
}
