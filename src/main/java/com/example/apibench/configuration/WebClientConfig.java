package com.example.apibench.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Configuration
public class WebClientConfig{
    @Value("${webclient.default.baseurl}")
    private final String baseUrl;
    @Bean
    WebClient getWebClient(){
        return WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }
}
