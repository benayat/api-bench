//package com.example.apibench.client;
//
//import com.example.apibench.exceptions.BadRequestException;
//import com.example.apibench.exceptions.FailedRequestException;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//@SpringBootTest
//public class BenchedApiClientTest {
//
//    @Autowired
//    private BenchedApiClient benchedApiClient;
//
//    @MockBean
//    @Qualifier("api-web-client")
//    private WebClient webClient;
//
//    @Test
//    public void sendBenchmarkRequestReturnVoid_ShouldNotThrowException() throws BadRequestException, FailedRequestException {
//        WebClient.RequestHeadersUriSpec requestHeadersUriSpec = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
//        WebClient.RequestHeadersSpec requestHeadersSpec = Mockito.mock(WebClient.RequestHeadersSpec.class);
//        WebClient.ResponseSpec responseSpec = Mockito.mock(WebClient.ResponseSpec.class);
//
//        Mockito.when(webClient.get()).thenReturn(requestHeadersUriSpec);
//        Mockito.when(requestHeadersUriSpec.uri(Mockito.anyString())).thenReturn(requestHeadersSpec);
//        Mockito.when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
//        Mockito.when(responseSpec.onStatus(Mockito.eq(HttpStatusCode::is5xxServerError), Mockito.any())).thenReturn(responseSpec);
//        Mockito.when(responseSpec.onStatus(Mockito.eq(HttpStatusCode::is4xxClientError), Mockito.any())).thenReturn(responseSpec);
//        Mockito.when(responseSpec.bodyToMono(Void.class)).thenReturn(Mono.empty());
//
//        benchedApiClient.sendBenchmarkRequestReturnVoid();
//    }
//}
