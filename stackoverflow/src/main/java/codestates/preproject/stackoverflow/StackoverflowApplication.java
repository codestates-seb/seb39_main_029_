package codestates.preproject.stackoverflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class StackoverflowApplication {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	static {
		System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
	}
	public static void main(String[] args) {
		SpringApplication.run(StackoverflowApplication.class, args);
	}

}
