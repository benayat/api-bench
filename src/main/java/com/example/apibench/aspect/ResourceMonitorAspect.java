package com.example.apibench.aspect;

import com.example.apibench.annotation.ResourceMonitor;
import com.example.apibench.client.DockerClient;
import com.example.apibench.model.BenchResponse;
import com.example.apibench.model.container_resources.ContainerResources;
import com.example.apibench.model.dto.ContainerResourcesDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.util.concurrent.Executors;

@Aspect
@Order(2)
@Component
@RequiredArgsConstructor
@Data
@Slf4j
public class ResourceMonitorAspect {
    private final DockerClient dockerClient;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private Disposable apiSubscription;
    private final ObjectMapper mapper;

    @Around("@annotation(resourceMonitor)")
    public BenchResponse logContainerResources(ProceedingJoinPoint joinPoint, ResourceMonitor resourceMonitor) throws Throwable {
        beginMonitoring();
        BenchResponse result = (BenchResponse) joinPoint.proceed();
        stopMonitoring();
        return result;
    }

    public void beginMonitoring() {
        log.info("started monitoring");
        Flux<String> responseMonoStream = dockerClient.getContainerStats();
        try (var executor = Executors.newSingleThreadExecutor()) {
            executor.submit(() ->
                    setApiSubscription(responseMonoStream.subscribe(stringData -> {
                                log.info("this is the received String: " + stringData);
                                ContainerResources resources;
                                try {
                                    resources = mapper.readValue(stringData, ContainerResources.class);
                                } catch (JsonProcessingException e) {
                                    throw new RuntimeException(e);
                                }
                                assert resources != null;
                                ContainerResourcesDto containerResourcesDto = new ContainerResourcesDto(resources);
                                simpMessagingTemplate.convertAndSend("/topic/monitor", containerResourcesDto);
                            },
                            error -> {
                                log.error("error from cause: " + error.getCause());
                                log.error("error message: " + error.getMessage());
                            })));
        }
    }

    public void stopMonitoring() {
        log.info("ending monitoring");
        getApiSubscription().dispose();
    }
}
