package com.example.apibench.model.container_resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CpuUsage {
    @JsonProperty("total_usage")
    private double containerCpuTotalUsage;
}
