package com.example.apibench.aspect;

import com.example.apibench.annotation.Timer;
import com.example.apibench.model.BenchResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Order(3)
@Component
@Slf4j
public class MethodTimingAspect {
    @Around("@annotation(timer)")
    public BenchResponse measureMethodTime(ProceedingJoinPoint joinPoint, Timer timer) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        BenchResponse result = (BenchResponse)joinPoint.proceed();
        stopWatch.stop();
        result.updateTime(stopWatch.getTotalTimeMillis());
        String methodName = joinPoint.getSignature().getName();
        log.info("Method " + methodName + " took " + stopWatch.getTotalTimeMillis() + " ms");
        return result;
    }
}
