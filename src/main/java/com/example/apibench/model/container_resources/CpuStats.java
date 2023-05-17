package com.example.apibench.model.container_resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CpuStats {
    @JsonProperty("cpu_usage")
    private CpuUsage cpuUsage;
    @JsonProperty("system_cpu_usage")
    private double systemCpu;
    @JsonProperty("online_cpus")
    private int numberOfCpus;
}
