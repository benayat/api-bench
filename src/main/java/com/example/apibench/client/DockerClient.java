package com.example.apibench.client;

import com.example.apibench.exceptions.BadRequestException;
import com.example.apibench.exceptions.FailedRequestException;
import com.example.apibench.model.ContainerResources;
import com.example.apibench.model.dto.ContainerResourcesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DockerClient {
//    need to specify and customize web client.
    private final WebClient webClient;

@Value("${webclient.default.docker.endpoint}")
    private final String endpoint;
    private final String containerId;

    public ContainerResourcesDto getContainerStats(){
        ContainerResources resources = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(endpoint)
                        .build(containerId))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(FailedRequestException::new))
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(BadRequestException::new))
                .bodyToMono(ContainerResources.class).block();
        assert resources != null;
        return new ContainerResourcesDto(resources);
    }


}
