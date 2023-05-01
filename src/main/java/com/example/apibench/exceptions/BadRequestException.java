package com.example.apibench.exceptions;

public class BadRequestException extends RuntimeException{
    public BadRequestException() {
        super("bad request");
    }
}
