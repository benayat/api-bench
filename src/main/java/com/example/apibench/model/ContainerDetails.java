package com.example.apibench.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ContainerDetails {
    @JsonProperty("Id")
    private String containerId;
}
