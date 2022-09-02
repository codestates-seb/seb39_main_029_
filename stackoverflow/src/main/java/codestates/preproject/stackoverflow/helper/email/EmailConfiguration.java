package codestates.preproject.stackoverflow.helper.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfiguration {
    @Value("${spring.mail.smtp.host}")
    private String host;

    @Value("${spring.mail.smtp.port}")
    private int port;

    @Value("${spring.mail.smtp.username}")
    private String username;

    @Value("${spring.mail.smtp.password}")
    private String password;

    @Value("${spring.mail.smtp.auth}")
    private String auth;

    @Value("${spring.mail.smtp.starttls.enable}")
    private String tlsEnable;

    @Bean
    public EmailSendable mockExceptionEmailSendable() {
        return new MockExceptionEmailSendable();
    }

    @Primary
    @Bean
    public EmailSendable simpleEmailSendable() {
        return new SimpleEmailSendable(javaMailSender());
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("spring.mail.smtp.auth", auth);
        props.put("spring.mail.smtp.starttls.enable", tlsEnable);
        mailSender.setJavaMailProperties(props);
        return mailSender;
    }
}
