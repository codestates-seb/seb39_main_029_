package codestates.preproject.stackoverflow.filter;

import codestates.preproject.stackoverflow.member.entity.Member;
import codestates.preproject.stackoverflow.member.repository.MemberRepository;
import codestates.preproject.stackoverflow.oauth.PrincipalDetails;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private MemberRepository memberRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository) {
        super(authenticationManager);
        this.memberRepository = memberRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("인증이나 권한이 필요한 주소 요청 됨.");

        String jwtHeader = request.getHeader("Authorization");

        if(jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
            System.out.println("토큰 문제");
            chain.doFilter(request, response);
            return;
        }

//        String[] url = request.getRequestURL().toString().split("/");

        String jwtToken = jwtHeader.replace("Bearer ", "");

//        if(url[url.length-1].equals("refresh")){
//            chain.doFilter(request,response);
//        }else{
            String username = JWT.require(Algorithm.HMAC512("cos_jwt_token")).build().verify(jwtToken).getClaim("nickName").asString();
        System.out.println("유효성 검증 통과");

            if (username != null) {
                Member memberEntity = memberRepository.findByNickName(username).get();
                System.out.println(memberEntity.getEmail() + "\n:이메일 인증 통과");
                PrincipalDetails principalDetails = new PrincipalDetails(memberEntity);
                Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("권한 인증 확인");

                chain.doFilter(request, response);
            }
//        }



//        ContextHolder -> context(auntication) context

    }
}