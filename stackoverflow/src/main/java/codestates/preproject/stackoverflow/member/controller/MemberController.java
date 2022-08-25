package codestates.preproject.stackoverflow.member.controller;

import codestates.preproject.stackoverflow.member.dto.MemberDto;


import codestates.preproject.stackoverflow.member.entity.Member;
import codestates.preproject.stackoverflow.member.mapper.MemberMapper;
import codestates.preproject.stackoverflow.member.service.MemberService;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;


import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/members")
@Validated
@Slf4j
public class MemberController {



    private MemberService memberService;

    private MemberMapper memberMapper;

    public MemberController(MemberService memberService, MemberMapper memberMapper){
        this.memberMapper = memberMapper;
        this.memberService = memberService;
    }

    @PostMapping("/join")
    public ResponseEntity joinMember(@Valid @RequestBody MemberDto.Join join){
        Member member = memberMapper.memberJoinToMember(join);
        memberService.createMember(member);
        Map<String,String> result = new HashMap<>(Map.of("result","완료"));
        return new ResponseEntity(result,HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity loginMember(@Valid @RequestBody MemberDto.Login login){
        Member member = memberMapper.memberLoginToMember(login);
        memberService.loginMember(member);
        Map<String,String> result = new HashMap<>(Map.of("result","완료"));
        return new ResponseEntity(result,HttpStatus.OK);
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

}
