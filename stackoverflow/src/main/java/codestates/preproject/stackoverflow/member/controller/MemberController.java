package codestates.preproject.stackoverflow.member.controller;

import codestates.preproject.stackoverflow.member.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/members")
@Validated
@Slf4j
public class MemberController {

    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post memberPostDto){
        Map<String, String> map = new HashMap<>();
        map.put("result","회원가입완료");

        return new ResponseEntity(map, HttpStatus.CREATED);
    }

}
