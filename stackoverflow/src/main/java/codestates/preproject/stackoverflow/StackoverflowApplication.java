package codestates.preproject.stackoverflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication(scanBasePackages = {"org.springframework.security.config.annotation.web.builders.HttpSecurity"})
@EnableScheduling
public class StackoverflowApplication {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
	public static void main(String[] args) {
		SpringApplication.run(StackoverflowApplication.class, args);
	}

}
