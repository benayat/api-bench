package com.example.apibench.model.dto;

import com.example.apibench.model.container_resources.ContainerResources;
import com.example.apibench.model.container_resources.CpuStats;
import com.example.apibench.model.container_resources.CpuUsage;
import com.example.apibench.model.container_resources.MemoryStats;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContainerResourcesDtoTest {
    private final double containerCpu = 10.0;
    private final double systemCpu = 100.0;
    private final int numberOfCpus = 2;
    private final double memoryInBytes = 1024 * 1024 * 2; // 2MB



    @Test
    public void testContainerResourcesDtoConstructor() {

        ContainerResourcesDto dto = new ContainerResourcesDto(containerCpu, systemCpu, numberOfCpus, memoryInBytes);

        assertEquals(10.0, dto.getCpuPercentage());
        assertEquals(200.0, dto.getCpuMilliCors());
        assertEquals(2.0, dto.getMemoryInMb());
    }

    @Test
    public void testContainerResourcesDtoFromResources() {
        ContainerResources resources = ContainerResources.builder()
                .memoryStats(MemoryStats.builder().memoryUsageInBytes(memoryInBytes).build())
                .cpuStats(CpuStats.builder()
                        .cpuUsage(CpuUsage.builder().containerCpuTotalUsage(containerCpu).build())
                        .systemCpu(systemCpu)
                        .numberOfCpus(numberOfCpus).build()).build();
        ContainerResourcesDto dto = new ContainerResourcesDto(resources);
        assertEquals(10.0, dto.getCpuPercentage());
        assertEquals(200.0, dto.getCpuMilliCors());
        assertEquals(2.0, dto.getMemoryInMb());
    }
}
