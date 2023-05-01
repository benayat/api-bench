package com.example.apibench.model.dto;

import com.example.apibench.model.ContainerResources;
import lombok.Data;

@Data
public class ContainerResourcesDto {

    private final int cpuPercentage;
    private final int memoryInMb;
    private final int cpuMilliCors;


    public ContainerResourcesDto(int cpuContainer, int cpuSystem, int numberOfCpus, int memoryRss) {
        this.cpuPercentage = 100 * cpuContainer / cpuSystem;
        this.cpuMilliCors = 10 * getCpuPercentage() * numberOfCpus;
        this.memoryInMb = memoryRss / (1024 * 1024);
    }
    public ContainerResourcesDto(ContainerResources resources) {
        this(resources.getCpuContainer(), resources.getCpuSystem(), resources.getNumberOfCpus(), resources.getMemoryRss());
    }
}
