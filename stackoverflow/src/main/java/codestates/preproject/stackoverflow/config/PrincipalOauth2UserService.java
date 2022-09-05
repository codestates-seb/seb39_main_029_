package codestates.preproject.stackoverflow.config;

import codestates.preproject.stackoverflow.member.entity.Member;
import codestates.preproject.stackoverflow.member.repository.MemberRepository;
import codestates.preproject.stackoverflow.oauth.PrincipalDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

//Oauth2 관련 기능 (Oauth2로 로그인하면 거치는 서비스)
@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String nickName = oAuth2User.getAttribute("name");
        String email = oAuth2User.getAttribute("email");
        String role = "ROLE_ADMIN";

        Optional<Member> memberEntity = memberRepository.findByEmail(email);
        Member member = new Member();
        if(memberEntity.isEmpty()){
            member.setNickName(nickName);
            member.setRoles(role);
            member.setEmail(email);
            memberRepository.save(member);
        }else{
            member = memberEntity.get();
        }

        return new PrincipalDetails(member, oAuth2User.getAttributes());
    }
}
