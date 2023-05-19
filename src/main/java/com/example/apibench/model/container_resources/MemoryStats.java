package com.example.apibench.model.container_resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemoryStats {
    @JsonProperty("usage")
    private double memoryUsageInBytes;
}

