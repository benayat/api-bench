package com.example.apibench.runner;

import com.example.apibench.model.BenchResponse;
import com.example.apibench.service.BenchmarkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(value=1)
@Slf4j
public class Runner implements CommandLineRunner {

    @Value("${bench.type}")
    private String benchType;
    @Value("${bench.requests}")
    private int numberOfRequests;
    @Value("${bench.users}")
    private int numberOfUsers;

    private final BenchmarkService benchmarkService;
    @Override
    public void run(String... args) {
        BenchResponse benchResponse = switch (benchType){
            case "single-thread":
                yield benchmarkService.singleThreadBenchmarks(numberOfRequests);
            case "multi-user":
                yield benchmarkService.multithreadedCustomUsers(numberOfUsers, numberOfRequests);
            case "multi-user-random":
                yield benchmarkService.multithreadedRandomUserBenchmark(numberOfRequests);
            default: yield null;
        };
        assert benchResponse != null;
        log.info(benchResponse.toString());
    }
}
