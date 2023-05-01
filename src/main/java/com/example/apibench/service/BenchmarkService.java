package com.example.apibench.service;

import com.example.apibench.annotation.Timer;
import com.example.apibench.client.BenchedApiClient;
import com.example.apibench.exceptions.BadRequestException;
import com.example.apibench.exceptions.FailedRequestException;
import com.example.apibench.model.BenchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class BenchmarkService {
    private final BenchedApiClient benchedApiClient;

    @Timer
    public BenchResponse singleThreadBenchmarks(int numberOfRequests) throws BadRequestException, FailedRequestException {
        for (int i = 0; i < numberOfRequests; i++) {
            benchedApiClient.sendBenchmarkRequestReturnVoid();
        }
        return new BenchResponse(numberOfRequests);
    }

    @Timer
    public BenchResponse multithreadedCustomUsers(int numberOfUsers, int requestsPerUser) {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, numberOfUsers).forEach(i -> executor.submit(() -> {
                for (int j = 0; j < requestsPerUser; j++) {
                    benchedApiClient.sendBenchmarkRequestReturnVoid();
                }
            }));
        }
        return new BenchResponse(numberOfUsers * requestsPerUser);
    }

    @Timer
    public BenchResponse multithreadedRandomUserBenchmark(int numberOfRequests) {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, numberOfRequests).forEach(i -> executor.submit(benchedApiClient::sendBenchmarkRequestReturnVoid));
        }
        return new BenchResponse(numberOfRequests);
    }
}
