package com.example.clientappdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomConfiguration {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
