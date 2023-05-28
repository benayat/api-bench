//package com.example.apibench.config;
//
//import com.example.apibench.configuration.ApiWebClientConfig;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//public class ApiWebClientConfigTest {
//
//    private ApiWebClientConfig apiWebClientConfig;
//    private String baseUrl = "http://test-url.com";
//
//    @BeforeEach
//    public void setup() {
//        apiWebClientConfig = new ApiWebClientConfig();
//        apiWebClientConfig.setBaseUrl(baseUrl); // Assuming you have a setter for this, otherwise you can mock the @Value injection with reflection
//    }
//
//    @Test
//    public void getWebClient() {
//        WebClient webClient = apiWebClientConfig.getWebClient();
//        assertNotNull(webClient);
//        assertEquals(baseUrl, webClient.baseUrl());
//    }
//}
