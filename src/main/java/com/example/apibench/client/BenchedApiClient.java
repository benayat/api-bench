package com.example.apibench.client;

import com.example.apibench.exceptions.BadRequestException;
import com.example.apibench.exceptions.FailedRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BenchedApiClient {
    @Value("${webclient.default.endpoint}")
    private final String endpoint;
    private final WebClient webClient;
    public void sendBenchmarkRequestReturnVoid(){
        webClient.get()
                .uri(endpoint)
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(FailedRequestException::new))
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(BadRequestException::new));
    }
}
