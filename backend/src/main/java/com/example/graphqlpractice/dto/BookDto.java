package com.example.graphqlpractice.dto;

import com.example.graphqlpractice.model.Book;

// DTO Response untuk Buku (Java 17 Record)
public record BookDto(Long id, String title, int year, Long authorId) {
    public static BookDto fromEntity(Book book) {
        if (book == null) return null;
        return new BookDto(book.getId(), book.getTitle(), book.getYear(), book.getAuthorId());
    }
}
