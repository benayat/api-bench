//package com.example.apibench.aspect;
//
//import com.example.apibench.annotation.ResourceMonitor;
//import com.example.apibench.client.DockerClient;
//import com.example.apibench.model.BenchResponse;
//import com.example.apibench.model.container_resources.ContainerResources;
//import com.example.apibench.model.container_resources.MemoryStats;
//import com.example.apibench.model.dto.ContainerResourcesDto;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import reactor.core.Disposable;
//import reactor.core.publisher.Flux;
//
//import java.lang.reflect.Type;
//
//import static org.junit.jupiter.api.Assertions.assertSame;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class ResourceMonitorAspectTest {
//
//    @Mock
//    private ProceedingJoinPoint proceedingJoinPoint;
//
//    @Mock
//    private MethodSignature methodSignature;
//
//    @InjectMocks
//    private ResourceMonitorAspect resourceMonitorAspect;
//
//    private final ObjectMapper mapper = new ObjectMapper();
//
//    private final String JSON_DATA = """
//            {
//              "memory_stats": {
//                    "usage": 48480256
//            },
//                "cpu_stats": {
//                    "cpu_usage": {
//                        "total_usage": 208071200
//                    },
//                    "system_cpu_usage": 1129304940000000,
//                    "online_cpus": 16,
//
//                }
//            }""";
//    @BeforeEach
//    public void setup() throws Throwable {
//        // Define the behavior of the mocked instance
//        ContainerResources containerResources = mapper.readValue(JSON_DATA, new TypeReference<>() {
//            @Override
//            public Type getType() {
//                return super.getType();
//            }
//        });
//        when(proceedingJoinPoint.getSignature()).thenReturn(methodSignature);
//        when(proceedingJoinPoint.proceed()).thenReturn(containerResources);
//
//    }
//
//    @Test
//    public void testLogContainerResources() throws Throwable {
//        BenchResponse benchResponse = new BenchResponse();
//        when(proceedingJoinPoint.proceed()).thenReturn(benchResponse);
//
//        BenchResponse result = resourceMonitorAspect.logContainerResources(joinPoint, resourceMonitor);
//
//        verify(resourceMonitorAspect).beginMonitoring();
//        verify(resourceMonitorAspect).stopMonitoring();
//        assertSame(result, benchResponse);
//    }
//
//    @Test
//    public void testStopMonitoring() {
//        Disposable disposable = mock(Disposable.class);
//        resourceMonitorAspect.setApiSubscription(disposable);
//        resourceMonitorAspect.stopMonitoring();
//        verify(disposable).dispose();
//    }
//}
