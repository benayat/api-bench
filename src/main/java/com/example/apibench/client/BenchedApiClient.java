package com.example.apibench.client;

import com.example.apibench.exceptions.BadRequestException;
import com.example.apibench.exceptions.FailedRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Scope("prototype")
@RequiredArgsConstructor
public class BenchedApiClient {
    @Value("${webclient.default.endpoint}")
    private String endpoint;
    @Qualifier("api-web-client")
    private final WebClient webClient;
    public void sendBenchmarkRequestReturnVoid() throws FailedRequestException, BadRequestException{
        sendBenchmarkRequest();
    }

    public Mono<String> sendBenchmarkRequestReturnMonoString(){
        WebClient.ResponseSpec responseSpec = sendBenchmarkRequest();
        return (Mono<String>) responseToMono(responseSpec, String.class);
    }
    public String sendBenchmarkRequestReturnString(){
        Mono<String> mono = sendBenchmarkRequestReturnMonoString();
        return mono.block();
    }
    private static Mono<?> responseToMono(WebClient.ResponseSpec responseSpec, Class<?> type){
        return responseSpec.bodyToMono(type);
    }
    private WebClient.ResponseSpec sendBenchmarkRequest(){
        return webClient.get()
                .uri(endpoint)
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(FailedRequestException::new))
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(BadRequestException::new));
    }

}
