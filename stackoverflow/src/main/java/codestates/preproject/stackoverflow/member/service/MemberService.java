package codestates.preproject.stackoverflow.member.service;

import codestates.preproject.stackoverflow.exception.BusinessLogicException;
import codestates.preproject.stackoverflow.exception.ExceptionCode;
import codestates.preproject.stackoverflow.member.entity.Member;
import codestates.preproject.stackoverflow.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MemberService {
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public void createMember(Member member){
        verifyExistsNickName(member.getNickName());
        memberRepository.save(member);
    }

    public void loginMember(Member member){
        Optional<Member> optionalMember = memberRepository.findByEmail(member.getEmail());
        Member findMember = optionalMember.orElseThrow(()->new BusinessLogicException(ExceptionCode.EMAIL_NOT_FOUND));
        if(!findMember.getPassword().equals(member.getPassword())){
            throw new BusinessLogicException(ExceptionCode.PASSWORD_NOT_FOUND);
        }
    }


    public Member updateMember(Member member){
        Member findMember = findVerifiedMember(member.getMemberid());

        Optional.ofNullable(member.getNickName()).ifPresent(nickName -> findMember.setNickName(nickName));
        Optional.ofNullable(member.getSelfId()).ifPresent(selfId -> findMember.setSelfId(selfId));
        Optional.ofNullable(member.getLocation()).ifPresent(location -> findMember.setLocation(location));
        Optional.ofNullable(member.getTitle()).ifPresent(title -> findMember.setTitle(title));

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
}
