package com.example.graphqlpractice.exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class CustomGraphQLExceptionHandler {

    // 1. Menangani Error: Data tidak ditemukan (NOT_FOUND)
    @GraphQlExceptionHandler
    public GraphQLError handleResourceNotFound(ResourceNotFoundException ex, DataFetchingEnvironment env) {
        return GraphqlErrorBuilder.newError(env)
                .message(ex.getMessage())
                .errorType(ErrorType.NOT_FOUND)
                .build();
    }

    // 2. Menangani Error: Validasi input yang tidak valid (BAD_REQUEST)
    @GraphQlExceptionHandler
    public GraphQLError handleIllegalArgument(IllegalArgumentException ex, DataFetchingEnvironment env) {
        return GraphqlErrorBuilder.newError(env)
                .message(ex.getMessage())
                .errorType(ErrorType.BAD_REQUEST)
                .build();
    }

    // 3. Menangani Error: Kesalahan umum server (INTERNAL_ERROR)
    @GraphQlExceptionHandler
    public GraphQLError handleGeneralException(Exception ex, DataFetchingEnvironment env) {
        return GraphqlErrorBuilder.newError(env)
                .message("Terjadi kesalahan internal pada server: " + ex.getMessage())
                .errorType(ErrorType.INTERNAL_ERROR)
                .build();
    }
}

