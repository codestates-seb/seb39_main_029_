package codestates.preproject.stackoverflow.oauth;

import codestates.preproject.stackoverflow.exception.BusinessLogicException;
import codestates.preproject.stackoverflow.exception.ExceptionCode;
import codestates.preproject.stackoverflow.member.entity.Member;
import codestates.preproject.stackoverflow.member.mapper.MemberMapper;
import codestates.preproject.stackoverflow.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member memberEntity = memberRepository.findByEmail(username).orElseThrow(()->
                new BusinessLogicException(ExceptionCode.EMAIL_NOT_FOUND));
        return new PrincipalDetails(memberEntity);
    }
}
