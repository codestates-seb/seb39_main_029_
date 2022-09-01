//package codestates.preproject.stackoverflow.filter;
//
//import codestates.preproject.stackoverflow.member.entity.Member;
//import codestates.preproject.stackoverflow.member.repository.MemberRepository;
//import codestates.preproject.stackoverflow.oauth.PrincipalDetails;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.GenericFilterBean;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.util.Base64;
//import java.util.Map;
//
//public class testfilter extends GenericFilterBean {
//    private final MemberRepository memberRepository;
//
//    public testfilter(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
//        String[] url = httpServletRequest.getRequestURL().toString().split("/");
//        String jwtHeader = httpServletRequest.getHeader("Authorization");
//
//        if(jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        if(url[url.length-1].equals("refresh")){
//            Base64.Decoder decoder = Base64.getDecoder();
//            String check = jwtHeader.split("[.]")[1];
//            String payload = new String(decoder.decode(check));
//            ObjectMapper mapper = new ObjectMapper();
//            Map<String, String> map = mapper.readValue(payload,Map.class);
//            String email = map.get("email");
//            Member member = memberRepository.findByEmail(email).get();
//            PrincipalDetails principalDetails = new PrincipalDetails(member);
//            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            chain.doFilter(request,response);
//        }else{
//            chain.doFilter(request, response);
//        }
//    }
//}
