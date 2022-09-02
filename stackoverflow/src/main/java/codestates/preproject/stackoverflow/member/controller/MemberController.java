package codestates.preproject.stackoverflow.member.controller;

import codestates.preproject.stackoverflow.member.dto.MemberDto;


import codestates.preproject.stackoverflow.member.entity.Member;
import codestates.preproject.stackoverflow.member.mapper.MemberMapper;
import codestates.preproject.stackoverflow.member.service.MemberService;


import codestates.preproject.stackoverflow.post.service.PostService;
/*import codestates.preproject.stackoverflow.s3.upload.S3Upload;*/
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;


import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.context.support.HttpRequestHandlerServlet;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Positive;


import java.io.IOException;

@RestController
@RequestMapping("/v1/members")
@Validated
@Slf4j
public class MemberController {
    private final MemberService memberService;

    private final MemberMapper memberMapper;

    private final PostService postService;
    /*private final S3Upload s3Upload;*/
    public MemberController(MemberService memberService, MemberMapper memberMapper, PostService postService){
        this.memberMapper = memberMapper;
        this.memberService = memberService;
        this.postService = postService;

    }

    @PostMapping("/join")
    public void joinMember(@Valid @RequestBody MemberDto.Join join, HttpServletResponse response) throws IOException {
        Member member = memberMapper.memberJoinToMember(join);

        String redirect_uri="/check";
        response.sendRedirect(redirect_uri);

    }
    @PostMapping("/check")
    public ResponseEntity checkMember(String code) {

         memberService.emailCheckMember(code);
         return new ResponseEntity<>(HttpStatus.CREATED);
    }



    @PostMapping("/login")
    public ResponseEntity loginMember(@Valid @RequestBody MemberDto.Login login){
        Member member = memberMapper.memberLoginToMember(login);
        Member findMember = memberService.loginMember(member);
        return new ResponseEntity(memberMapper.memberToMemberResponseDto(findMember), HttpStatus.OK);
    }


    @PatchMapping("/update/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") @Positive long memberid,
                                      @RequestBody MemberDto.Patch patch,
            @RequestParam("images") MultipartFile multipartFile) throws IOException {
        patch.setMemberid(memberid);
        Member member = memberMapper.memberPatchToMember(patch);
        Member updateMember = memberService.updateMember(member);
        MemberDto.Response result = memberMapper.memberToMemberResponseDto(updateMember);
        /*String imageUrl = s3Upload.upload(multipartFile);
        result.setImageUrl(imageUrl);*/
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
