package com.example.apibench.model.container_resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@AllArgsConstructor
@Jacksonized
public class CpuUsage {
    @JsonProperty("total_usage")
    private double containerCpuTotalUsage;
}
