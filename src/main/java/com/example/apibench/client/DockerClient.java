package com.example.apibench.client;

import com.example.apibench.exceptions.BadRequestException;
import com.example.apibench.exceptions.FailedRequestException;
import com.example.apibench.model.ContainerDetails;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
@Data
public class DockerClient {
    @Qualifier("docker-web-client")
    private final WebClient webClient;
    @Value("${docker-client.default.endpoint}")
    private String endpoint;
    @Value("${docker-client.default.container-name}")
    private String containerName;
    @Value("${docker-client.default.container-id-endpoint}")
    private String containerIdEndpoint;
    private String containerId;

    @PostConstruct
    public void fetchContainerIdFromName() {
        ContainerDetails containerDetails = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(containerIdEndpoint)
                        .build(containerName))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(FailedRequestException::new))
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(BadRequestException::new))
                .bodyToMono(ContainerDetails.class).block();
        assert containerDetails != null;
        setContainerId(containerDetails.getContainerId());
    }

    public Flux<String> getContainerStats() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(endpoint)
                        .build(containerId))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(FailedRequestException::new))
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(BadRequestException::new))
                .bodyToFlux(String.class);
    }
}
