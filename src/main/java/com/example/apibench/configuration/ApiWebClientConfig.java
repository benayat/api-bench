package com.example.apibench.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Configuration
public class ApiWebClientConfig {
    @Value("${webclient.default.baseurl}")
    private String baseUrl;
    @Bean("api-web-client")
    WebClient getWebClient(){
        return WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }
}
