package com.example.apibench.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class DockerClient {
//    need to specify and customize web client.
    private final WebClient webClient;
}
