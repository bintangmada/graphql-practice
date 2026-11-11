package com.example.graphqlpractice.service;

import com.example.graphqlpractice.dto.BookDto;
import com.example.graphqlpractice.exception.ResourceNotFoundException;
import com.example.graphqlpractice.model.Book;
import com.example.graphqlpractice.repository.AuthorRepository;
import com.example.graphqlpractice.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Transactional(readOnly = true)
    public List<BookDto> getBooks(String search) {
        List<Book> books;
        if (search != null && !search.isBlank()) {
            books = bookRepository.findByTitleContainingIgnoreCase(search);
        } else {
            books = bookRepository.findAll();
        }

        return books.stream()
                .map(BookDto::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public BookDto getBookById(Long id) {
        return bookRepository.findById(id)
                .map(BookDto::fromEntity)
                .orElse(null);
    }

    // Mengambil buku milik seorang penulis menggunakan authorId biasa (tanpa JPA join)
    @Transactional(readOnly = true)
    public List<BookDto> getBooksByAuthorId(Long authorId) {
        return bookRepository.findByAuthorId(authorId)
                .stream()
                .map(BookDto::fromEntity)
                .toList();
    }

    public BookDto createBook(String title, int year, Long authorId) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Judul buku tidak boleh kosong!");
        }

        // Cukup validasi keberadaan ID penulis
        if (!authorRepository.existsById(authorId)) {
            throw new ResourceNotFoundException("Penulis dengan ID " + authorId + " tidak ditemukan!");
        }

        // Simpan langsung dengan authorId biasa
        Book book = new Book(title, year, authorId);
        Book saved = bookRepository.save(book);
        return BookDto.fromEntity(saved);
    }

    public BookDto updateBook(Long id, String title, Integer year) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Buku dengan ID " + id + " tidak ditemukan!"));

        if (title != null && !title.isBlank()) {
            book.setTitle(title);
        }
        if (year != null) {
            book.setYear(year);
        }

        Book updated = bookRepository.save(book);
        return BookDto.fromEntity(updated);
    }

    public boolean deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Buku dengan ID " + id + " tidak ditemukan!");
        }
        bookRepository.deleteById(id);
        return true;
    }
}
