package com.xyy.cache.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;
@Configuration
public class MailConfig {

    @Bean
    public JavaMailSenderImpl javaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.qq.com");
        javaMailSender.setUsername("1562016984@qq.com");
        javaMailSender.setPassword("vajgtbobfxggbage");
        javaMailSender.setDefaultEncoding("UTF-8");
        Properties properties = new Properties();
        properties.put("mail.smtp.starttls.enable", true);
        javaMailSender.setJavaMailProperties(properties);
        return javaMailSender;
    }
}
