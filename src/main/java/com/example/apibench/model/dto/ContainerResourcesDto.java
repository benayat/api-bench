package com.example.apibench.model.dto;

import com.example.apibench.model.container_resources.ContainerResources;
import lombok.Data;

@Data
public class ContainerResourcesDto {

    private final double cpuPercentage;
    private final double memoryInMb;
    private final double cpuMilliCors;


    public ContainerResourcesDto(double containerCpu, double systemCpu, long numberOfCpus, double memoryInBytes) {
        this.cpuPercentage = 100.0 * (containerCpu / systemCpu);
        this.cpuMilliCors = 10.0 * getCpuPercentage() * numberOfCpus;
        this.memoryInMb = memoryInBytes / (1024 * 1024);
    }

    public ContainerResourcesDto(ContainerResources resources) {
        this(resources.getCpuStats().getCpuUsage().getContainerCpuTotalUsage(),
                resources.getCpuStats().getSystemCpu(),
                resources.getCpuStats().getNumberOfCpus(),
                resources.getMemoryStats().getMemoryUsageInBytes());
    }
}
