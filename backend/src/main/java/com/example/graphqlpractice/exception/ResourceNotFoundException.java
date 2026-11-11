package com.example.graphqlpractice.exception;

// Custom RuntimeException saat data tidak ditemukan di database
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

