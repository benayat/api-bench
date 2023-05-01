package com.example.apibench.exceptions;

public class FailedRequestException extends RuntimeException{
    public FailedRequestException() {
        super("failed request");
    }
}
