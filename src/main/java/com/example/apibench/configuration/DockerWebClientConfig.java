package com.example.apibench.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class DockerWebClientConfig {
    @Value("${docker-client.default.baseurl}")
    private String baseUrl;
    @Bean("docker-web-client")
    WebClient getWebClient(){
        return WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }
}
