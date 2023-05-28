package com.example.apibench.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class ContainerDetails {
    @JsonProperty("Id")
    private String containerId;
}
