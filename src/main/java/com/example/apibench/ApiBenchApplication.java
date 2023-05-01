package com.example.apibench;

import com.example.apibench.service.BenchmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiBenchApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiBenchApplication.class, args);
    }
}
