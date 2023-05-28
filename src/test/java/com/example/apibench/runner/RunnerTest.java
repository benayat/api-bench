//package com.example.apibench.runner;
//
//import com.example.apibench.model.BenchResponse;
//import com.example.apibench.service.BenchmarkService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.test.util.ReflectionTestUtils;
//
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class RunnerTest {
//
//    @Mock
//    private BenchmarkService benchmarkService;
//
//    @InjectMocks
//    private Runner runner;
//
//    private final int numberOfRequests = 10;
//    private final int numberOfUsers = 5;
//    private BenchResponse benchResponse;
//
//    @BeforeEach
//    void setUp() {
//        ReflectionTestUtils.setField(runner, "numberOfRequests", numberOfRequests);
//        ReflectionTestUtils.setField(runner, "numberOfUsers", numberOfUsers);
//        benchResponse = new BenchResponse();
//        when(benchmarkService.singleThreadBenchmarks(numberOfRequests)).thenReturn(benchResponse);
//        when(benchmarkService.multithreadedCustomUsers(numberOfUsers, numberOfRequests)).thenReturn(benchResponse);
//        when(benchmarkService.multithreadedRandomUserBenchmark(numberOfRequests)).thenReturn(benchResponse);
//    }
//
//    @Test
//    void runSingleThreadTest() {
//        ReflectionTestUtils.setField(runner, "benchType", "single-thread");
//        runner.run();
//
//        verify(benchmarkService).singleThreadBenchmarks(numberOfRequests);
//        verify(benchmarkService, never()).multithreadedCustomUsers(numberOfUsers, numberOfRequests);
//        verify(benchmarkService, never()).multithreadedRandomUserBenchmark(numberOfRequests);
//    }
//
//    @Test
//    void runMultiUserTest() {
//        ReflectionTestUtils.setField(runner, "benchType", "multi-user");
//        runner.run();
//
//        verify(benchmarkService, never()).singleThreadBenchmarks(numberOfRequests);
//        verify(benchmarkService).multithreadedCustomUsers(numberOfUsers, numberOfRequests);
//        verify(benchmarkService, never()).multithreadedRandomUserBenchmark(numberOfRequests);
//    }
//
//    @Test
//    void runMultiUserRandomTest() {
//        ReflectionTestUtils.setField(runner, "benchType", "multi-user-random");
//        runner.run();
//
//        verify(benchmarkService, never()).singleThreadBenchmarks(numberOfRequests);
//        verify(benchmarkService, never()).multithreadedCustomUsers(numberOfUsers, numberOfRequests);
//        verify(benchmarkService).multithreadedRandomUserBenchmark(numberOfRequests);
//    }
//
//    @Test
//    void runWithUnrecognizedBenchTypeTest() {
//        ReflectionTestUtils.setField(runner, "benchType", "unrecognized-type");
//        runner.run();
//
//        verify(benchmarkService, never()).singleThreadBenchmarks(numberOfRequests);
//        verify(benchmarkService, never()).multithreadedCustomUsers(numberOfUsers, numberOfRequests);
//        verify(benchmarkService, never()).multithreadedRandomUserBenchmark(numberOfRequests);
//    }
//}
