package com.example.apibench.service;

import com.example.apibench.client.BenchedApiClient;
import com.example.apibench.model.BenchResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BenchmarkServiceTest {

    @Mock
    private BenchedApiClient benchedApiClient;

    @InjectMocks
    private BenchmarkService benchmarkService;

    @Test
    void singleThreadBenchmarksTest() {
        int numberOfRequests = 5;
        BenchResponse response = benchmarkService.singleThreadBenchmarks(numberOfRequests);

        verify(benchedApiClient, times(numberOfRequests)).sendBenchmarkRequestReturnVoid();
        assertEquals(numberOfRequests, response.getNumberOfRequests());
    }

    @Test
    void multithreadedCustomUsersTest() {
        int numberOfUsers = 3;
        int requestsPerUser = 4;
        BenchResponse response = benchmarkService.multithreadedCustomUsers(numberOfUsers, requestsPerUser);

        verify(benchedApiClient, times(numberOfUsers * requestsPerUser)).sendBenchmarkRequestReturnVoid();
        assertEquals(numberOfUsers * requestsPerUser, response.getNumberOfRequests());
    }

    @Test
    void multithreadedRandomUserBenchmarkTest() {
        int numberOfRequests = 7;
        BenchResponse response = benchmarkService.multithreadedRandomUserBenchmark(numberOfRequests);

        verify(benchedApiClient, times(numberOfRequests)).sendBenchmarkRequestReturnVoid();
        assertEquals(numberOfRequests, response.getNumberOfRequests());
    }
}

