package com.example.apibench.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BenchResponse {
    long timeInMs;
    int numberOfRequests;
    double requestsPerSecond;

    public BenchResponse( int numberOfRequests) {
        this.numberOfRequests = numberOfRequests;
    }
    public void updateTime(long timeInMs){
        setTimeInMs(timeInMs);
        setRequestsPerSecond((double) getNumberOfRequests()*1000/timeInMs);
    }
}
