package codestates.preproject.stackoverflow.filter;

import codestates.preproject.stackoverflow.oauth.PrincipalDetails;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class SuccesOauth2 extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        String jwtToken = JWT.create()
                .withSubject("cos jwt token")
//                .withExpiresAt(new Date(System.currentTimeMillis() + (60 * 1000 * 60 * 24)))
                .withExpiresAt(new Date(System.currentTimeMillis() + (60 * 1000)))
                .withClaim("email", principalDetails.getMember().getEmail())
                .withClaim("nickName", principalDetails.getMember().getNickName())
                .sign(Algorithm.HMAC512("cos_jwt_token"));
        response.addHeader("Authorization", "Bearer " + jwtToken);
        response.addHeader("Memberid", String.valueOf(principalDetails.getMember().getMemberid()));
        System.out.println(principalDetails.getMember().getEmail());
        System.out.println(principalDetails.getMember().getNickName());
        System.out.println(String.valueOf(principalDetails.getMember().getMemberid()));
        clearAuthenticationAttributes(request);
    }
}



