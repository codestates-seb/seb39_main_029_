package codestates.preproject.stackoverflow.config;

import codestates.preproject.stackoverflow.filter.JwtAuthenticationFilter;
import codestates.preproject.stackoverflow.filter.JwtAuthorizationFilter;
//import codestates.preproject.stackoverflow.filter.testfilter;
import codestates.preproject.stackoverflow.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
//@RequiredArgsConstructor
public class SecurityConfig {
    @Autowired
    private CorsFilter corsFilter;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.cors();
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
//                .addFilterBefore(new testfilter(memberRepository), UsernamePasswordAuthenticationFilter.class)
                .apply(new CustomDsl())
                .and()
                .authorizeRequests()
//                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
//                .anyRequest().authenticated()
                .antMatchers("/v1/members/myPage/**","/v1/members/update/**","/v1/members/delete/**","/v1/members/refresh","/v1/posts/**","/v1/comments/**","/v1/tags")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();

        return http.build();
    }

    public class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity> {

        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
            builder
                    .addFilter(corsFilter)
                    .addFilter(new JwtAuthenticationFilter(authenticationManager, memberRepository, bCryptPasswordEncoder))
                    .addFilter(new JwtAuthorizationFilter(authenticationManager, memberRepository));
        }
    }
}