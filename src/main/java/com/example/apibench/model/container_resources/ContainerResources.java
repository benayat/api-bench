package com.example.apibench.model.container_resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ContainerResources {
    @JsonProperty("memory_stats")
    private MemoryStats memoryStats;
    @JsonProperty("cpu_stats")
    private CpuStats cpuStats;

}
/*
 * {"read":"2023-05-01T18:06:16.152161115Z","preread":"2023-05-01T18:06:15.144584574Z","pids_stats":{"current":21},"blkio_stats":{"io_service_bytes_recursive":[],"io_serviced_recursive":[],"io_queue_recursive":[],"io_service_time_recursive":[],"io_wait_time_recursive":[],"io_merged_recursive":[],"io_time_recursive":[],"sectors_recursive":[]},"num_procs":0,"storage_stats":{},"cpu_stats":{"cpu_usage":{"total_usage":208071200,"percpu_usage":[711400,825600,6748600,1048500,177066400,0,0,4918000,1277700,317300,1049600,0,12969300,0,1138800,0],"usage_in_kernelmode":30000000,"usage_in_usermode":140000000},"system_cpu_usage":1129304940000000,"online_cpus":16,"throttling_data":{"periods":0,"throttled_periods":0,"throttled_time":0}},"precpu_stats":{"cpu_usage":{"total_usage":207788300,"percpu_usage":[711400,825600,6578600,1048500,177066400,0,0,4918000,1277700,317300,1049600,0,12856400,0,1138800,0],"usage_in_kernelmode":30000000,"usage_in_usermode":140000000},"system_cpu_usage":1129288820000000,"online_cpus":16,"throttling_data":{"periods":0,"throttled_periods":0,"throttled_time":0}},"memory_stats":{"usage":48480256,"max_usage":49135616,"stats":{"active_anon":4096,"active_file":12288,"cache":12288,"dirty":0,"hierarchical_memory_limit":9223372036854771712,"hierarchical_memsw_limit":9223372036854771712,"inactive_anon":47161344,"inactive_file":0,"mapped_file":0,"pgfault":7357,"pgmajfault":0,"pgpgin":6717,"pgpgout":819,"rss":47169536,"rss_huge":23068672,"total_active_anon":4096,"total_active_file":12288,"total_cache":12288,"total_dirty":0,"total_inactive_anon":47161344,"total_inactive_file":0,"total_mapped_file":0,"total_pgfault":7357,"total_pgmajfault":0,"total_pgpgin":6717,"total_pgpgout":819,"total_rss":47169536,"total_rss_huge":23068672,"total_unevictable":0,"total_writeback":0,"unevictable":0,"writeback":0},"limit":16121094144},"name":"/sleepy_cray","id":"21c58d23045f883fb76acedfe5c493a8031ff8cace93615832ae70bbe56f7e87","networks":{"eth0":{"rx_bytes":946,"rx_packets":11,"rx_errors":0,"rx_dropped":0,"tx_bytes":0,"tx_packets":0,"tx_errors":0,"tx_dropped":0}}}
 * */