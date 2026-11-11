package com.example.graphqlpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GraphqlPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphqlPracticeApplication.class, args);
        System.out.println("\nServer running at http://localhost:8080");
    }
}

