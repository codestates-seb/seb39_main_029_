package codestates.preproject.stackoverflow.member.service;

import codestates.preproject.stackoverflow.comments.entity.Comments;
import codestates.preproject.stackoverflow.exception.BusinessLogicException;
import codestates.preproject.stackoverflow.exception.ExceptionCode;
import codestates.preproject.stackoverflow.member.dto.MemberDto;
import codestates.preproject.stackoverflow.member.entity.Member;
import codestates.preproject.stackoverflow.member.repository.MemberRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class MemberService {
    private MemberRepository memberRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void createMember(Member member){
        verifyExistsNickName(member.getNickName());
        String password = member.getPassword();
        String BCypassord = bCryptPasswordEncoder.encode(password);
        member.setPassword(BCypassord);
        member.setRoles("ROLE_ADMIN");
        memberRepository.save(member);
    }

    public Member loginMember(Member member){
        Optional<Member> optionalMember = memberRepository.findByEmail(member.getEmail());
        Member findMember = optionalMember.orElseThrow(()->new BusinessLogicException(ExceptionCode.EMAIL_NOT_FOUND));
        if(!findMember.getPassword().equals(bCryptPasswordEncoder.encode(member.getPassword()))){
            throw new BusinessLogicException(ExceptionCode.PASSWORD_NOT_FOUND);
        }
        return findMember;
    }

    public void updateRep(long memberid){
        Member member = findVerifiedMember(memberid);
        member.setReputation(member.getReputation()+1);
        memberRepository.save(member);
    }

    public void downRep(long memberid){
        Member member = findVerifiedMember(memberid);
        member.setReputation(member.getReputation()-1);
        memberRepository.save(member);
    }


    public Member updateMember(Member member){
        Member findMember = findVerifiedMember(member.getMemberid());

        Optional.ofNullable(member.getNickName()).ifPresent(nickName -> findMember.setNickName(nickName));
        Optional.ofNullable(member.getSelfId()).ifPresent(selfId -> findMember.setSelfId(selfId));
        Optional.ofNullable(member.getLocation()).ifPresent(location -> findMember.setLocation(location));
        Optional.ofNullable(member.getTitle()).ifPresent(title -> findMember.setTitle(title));
        Optional.ofNullable(member.getPassword()).ifPresent(password -> findMember.setPassword(password));

        return memberRepository.save(findMember);
    }

    public void deleteMember(long memberid){
        Member findMember = findVerifiedMember(memberid);
        memberRepository.delete(findMember);
    }

    public Member findVerifiedMember(long memberid){
        Optional<Member> optionalMember = memberRepository.findById(memberid);
        Member findMember = optionalMember.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }

    public void verifyExistsNickName(String nickName) {
        Optional<Member> member = memberRepository.findByNickName(nickName);
        if(member.isPresent())
            //비지니스 로직으로 추후 변경
            throw new BusinessLogicException(ExceptionCode.NICKNAME_EXISTS);
    }

    public String refresh(MemberDto.Refresh refresh, HttpServletResponse response){
        Member member = memberRepository.findById(refresh.getMemberid()).get();
        String jwtToken = JWT.create()
                .withSubject("cos jwt token")
                .withExpiresAt(new Date(System.currentTimeMillis() + (60 * 1000)))
                .withClaim("email", member.getEmail())
                .withClaim("nickName", member.getNickName())
                .sign(Algorithm.HMAC512("cos_jwt_token"));
        StringBuilder sb = new StringBuilder(jwtToken);
        sb.insert(0,"Bearer ");
        return sb.toString();
    }
}
