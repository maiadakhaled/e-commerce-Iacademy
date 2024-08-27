package com.example.e_commerce.configs;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.context.ShutdownEndpoint;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppConfig  {

    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Bean
    public SecurityFilterChain actuatorFilterChain(HttpSecurity http) throws Exception {

        http.securityMatcher(EndpointRequest.toAnyEndpoint().excluding(ShutdownEndpoint.class));
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/manage/**").permitAll()
                .anyRequest().permitAll()
        );
        return http.build();
    }

}
