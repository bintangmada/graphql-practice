package com.example.graphqlpractice.dto;

import com.example.graphqlpractice.model.Author;

// DTO Response untuk Penulis (Java 17 Record)
public record AuthorDto(Long id, String name) {
    public static AuthorDto fromEntity(Author author) {
        if (author == null) return null;
        return new AuthorDto(author.getId(), author.getName());
    }
}

