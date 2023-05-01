package com.example.apibench.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BenchResponse {
    long timeInMs;
    boolean success;
    int numberOfRequests;
    double requestsPerSecond;

    public BenchResponse(boolean success, int numberOfRequests) {
        this.success = success;
        this.numberOfRequests = numberOfRequests;
    }
    public void updateTime(long timeInMs){
        setTimeInMs(timeInMs);
        setRequestsPerSecond((double) getNumberOfRequests()*1000/timeInMs);
    }
}
