package com.example.apibench.aspect;

import com.example.apibench.annotation.Timer;
import com.example.apibench.model.BenchResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MethodTimingAspectTest {

    @Mock
    private ProceedingJoinPoint proceedingJoinPoint;

    @Mock
    private MethodSignature methodSignature;

    @InjectMocks
    private MethodTimingAspect methodTimingAspect;


    @BeforeEach
    public void setup() throws Throwable {
        // Define the behavior of the mocked instance
        when(proceedingJoinPoint.getSignature()).thenReturn(methodSignature);
        when(proceedingJoinPoint.proceed()).thenReturn(new BenchResponse(1000));

    }
    @Test
    public void measureMethodTimeTest() throws Throwable {
        // Invoke the method to test
        BenchResponse benchResponse = methodTimingAspect.measureMethodTime(proceedingJoinPoint, mock(Timer.class));

        verify(proceedingJoinPoint, times(1)).proceed();
        BenchResponse benchResponseCompare = new BenchResponse(1000);
        benchResponseCompare.updateTime(benchResponse.getTimeInMs());
        // Verify the interactions with the mock
        Assertions.assertEquals(1000, benchResponse.getNumberOfRequests());
        Assertions.assertEquals((double) benchResponse.getNumberOfRequests() * 1000 / benchResponse.getTimeInMs(), benchResponse.getRequestsPerSecond());
        Assertions.assertEquals(benchResponse, benchResponseCompare);
    }
}




