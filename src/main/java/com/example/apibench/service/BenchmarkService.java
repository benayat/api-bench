package com.example.apibench.service;

import com.example.apibench.annotation.ResourceMonitor;
import com.example.apibench.annotation.Timer;
import com.example.apibench.client.BenchedApiClient;
import com.example.apibench.exceptions.BadRequestException;
import com.example.apibench.exceptions.FailedRequestException;
import com.example.apibench.model.BenchResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Slf4j
@Getter
@Setter
public class BenchmarkService {
    private final BenchedApiClient benchedApiClient;

    @Timer
    @ResourceMonitor
    public BenchResponse singleThreadBenchmarks(int numberOfRequests) throws BadRequestException, FailedRequestException {
        for (int i = 0; i < numberOfRequests; i++) {
            benchedApiClient.sendBenchmarkRequestReturnVoid();
        }
        return new BenchResponse(numberOfRequests);
    }
//    @Timer
//    @ResourceMonitor
//    public BenchResponse multithreadedCustomUsersByTime(int numberOfUsers, int timeInSeconds) {
//        try (var executor = new StructuredTaskScope.ShutdownOnFailure()) {
//            IntStream.range(0, numberOfUsers).forEach(i -> executor.fork(() -> {
//
//                    while(!Thread.currentThread().isInterrupted()){
//                    benchedApiClient.sendBenchmarkRequestReturnVoid();
//                }
//            }));
//        }
//        return new BenchResponse(numberOfUsers * requestsPerUser);
//    }

    @Timer
    @ResourceMonitor
    public BenchResponse multithreadedCustomUsers(int numberOfUsers, int requestsPerUser) {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, numberOfUsers).forEach(i -> executor.submit(() -> {
                for (int j = 0; j < requestsPerUser; j++) {
                    benchedApiClient.sendBenchmarkRequestReturnVoid();
                }
            }));
        } catch (BadRequestException | FailedRequestException e){
            log.error(e.getMessage());
        }
        return new BenchResponse(numberOfUsers * requestsPerUser);
    }

    @Timer
    @ResourceMonitor
    public BenchResponse multithreadedRandomUserBenchmark(int numberOfRequests) {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, numberOfRequests).forEach(i -> executor.submit(benchedApiClient::sendBenchmarkRequestReturnVoid));
        }
        return new BenchResponse(numberOfRequests);
    }
}
