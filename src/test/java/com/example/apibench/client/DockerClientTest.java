//package com.example.apibench.client;
//
//import com.example.apibench.exceptions.BadRequestException;
//import com.example.apibench.exceptions.FailedRequestException;
//import com.example.apibench.model.ContainerDetails;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//public class DockerClientTest {
//
//    @Autowired
//    DockerClient dockerClient;
//
//    @MockBean
//    @Qualifier("docker-web-client")
//    WebClient webClient;
//
//    @Test
//    void fetchContainerIdFromName_SuccessfulRequest() throws BadRequestException, FailedRequestException {
//        // setup
//        ContainerDetails mockContainerDetails = new ContainerDetails("mockContainerId");
//        setUpMockWebClient(HttpStatusCode.OK, Mono.just(mockContainerDetails));
//
//        // action
//        dockerClient.fetchContainerIdFromName();
//
//        // verify
//        verify(webClient, times(1)).get();
//        assertEquals(dockerClient.getContainerId(), "mockContainerId");
//    }
//
//    @Test
//    void fetchContainerIdFromName_ServerError() {
//        // setup
//        setUpMockWebClient(HttpStatusCode.INTERNAL_SERVER_ERROR, Mono.error(new FailedRequestException()));
//
//        // action & assert
//        assertThrows(FailedRequestException.class, () -> dockerClient.fetchContainerIdFromName());
//    }
//
//    @Test
//    void fetchContainerIdFromName_ClientError() {
//        // setup
//        setUpMockWebClient(HttpStatusCode.BAD_REQUEST, Mono.error(new BadRequestException()));
//
//        // action & assert
//        assertThrows(BadRequestException.class, () -> dockerClient.fetchContainerIdFromName());
//    }
//
//    @Test
//    void getContainerStats_SuccessfulRequest() throws BadRequestException, FailedRequestException {
//        // setup
//        setUpMockWebClient(HttpStatusCode.OK, Flux.just("mockStat1", "mockStat2"));
//
//        // action
//        Flux<String> containerStatsFlux = dockerClient.getContainerStats();
//
//        // verify
//        verify(webClient, times(1)).get();
//        assertEquals(containerStatsFlux.blockFirst(), "mockStat1");
//    }
//
//    // Continue with similar tests for getContainerStats encountering server/client errors...
//
//    private <T> void setUpMockWebClient(HttpStatusCode statusCode, T mockResponse) {
//        WebClient.RequestHeadersUriSpec requestHeadersUriSpec = mock(WebClient.RequestHeadersUriSpec.class);
//        WebClient.RequestHeadersSpec requestHeadersSpec = mock(WebClient.RequestHeadersSpec.class);
//        WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);
//
//        when(webClient.get()).thenReturn(requestHeadersUriSpec);
//        when(requestHeadersUriSpec.uri(any())).thenReturn(requestHeadersSpec);
//        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
//        when(responseSpec.onStatus(eq(statusCode::is5xxServerError), any())).thenReturn(responseSpec);
//        when(responseSpec.onStatus(eq(statusCode::is4xxClientError), any())).thenReturn(responseSpec);
//
//        if (mockResponse instanceof Mono) {
//            when(responseSpec.bodyToMono(ContainerDetails.class)).thenReturn((Mono<ContainerDetails>) mockResponse);
//        } else if (mockResponse instanceof Flux) {
//            when(responseSpec.bodyToFlux(String.class)).thenReturn((Flux<String>) mockResponse);
//        }
//    }
//}
