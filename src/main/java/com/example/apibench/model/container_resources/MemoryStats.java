package com.example.apibench.model.container_resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MemoryStats {
    @JsonProperty("usage")
    private double memoryUsageInBytes;
}

