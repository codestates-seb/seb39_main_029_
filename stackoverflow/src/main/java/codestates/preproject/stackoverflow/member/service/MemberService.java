package codestates.preproject.stackoverflow.member.service;

import codestates.preproject.stackoverflow.exception.BusinessLogicException;
import codestates.preproject.stackoverflow.exception.ExceptionCode;
import codestates.preproject.stackoverflow.helper.email.entity.Email;
import codestates.preproject.stackoverflow.helper.email.repository.EmailRepository;
import codestates.preproject.stackoverflow.helper.event.MemberRegistrationApplicationEvent;
import codestates.preproject.stackoverflow.member.entity.Member;
import codestates.preproject.stackoverflow.member.repository.MemberRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class MemberService {
    private MemberRepository memberRepository;
    private final ApplicationEventPublisher publisher;
    private final EmailRepository emailRepository;

    private Random random = new Random();
    public MemberService(MemberRepository memberRepository, ApplicationEventPublisher publisher, EmailRepository emailRepository){
        this.memberRepository = memberRepository;
        this.publisher = publisher;
        this.emailRepository = emailRepository;
    }

    public void createMember(Member member){
        verifyExistsNickName(member.getNickName());
        String code = random.nextInt()+"";

        publisher.publishEvent(new MemberRegistrationApplicationEvent(this, member,code));
        Email email = new Email();
        email.setCode(code);
        email.setPassword(member.getPassword());
        email.setNickName(member.getNickName());
        email.setEmail(member.getEmail());
        emailRepository.save(email);
    }

    public void emailCheckMember(String code) {
        Optional<Email> result = emailRepository.findByCodes(code);
        System.out.println(result.get().getCode());
        if(result.isPresent()){
            Email email = result.get();
            Member member = new Member();
            member.setEmail(email.getEmail());
            member.setNickName(email.getNickName());
            member.setPassword(email.getPassword());
            memberRepository.save(member);
        }else{
            throw new BusinessLogicException(ExceptionCode.CODE_NOT_FOUND);
        }

    }

    public Member loginMember(Member member){
        Optional<Member> optionalMember = memberRepository.findByEmail(member.getEmail());
        Member findMember = optionalMember.orElseThrow(()->new BusinessLogicException(ExceptionCode.EMAIL_NOT_FOUND));
        if(!findMember.getPassword().equals(member.getPassword())){
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
}
