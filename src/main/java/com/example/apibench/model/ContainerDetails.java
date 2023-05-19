package com.example.apibench.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContainerDetails {
    @JsonProperty("Id")
    private String containerId;
}
