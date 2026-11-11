package com.example.graphqlpractice.controller;

import com.example.graphqlpractice.dto.AuthorDto;
import com.example.graphqlpractice.dto.BookDto;
import com.example.graphqlpractice.service.AuthorService;
import com.example.graphqlpractice.service.BookService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    // Inject Service Layer (Bukan Repository langsung!)
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    // ==========================================
    // 1. QUERY MAPPINGS (Membaca DTO via Service)
    // ==========================================

    @QueryMapping
    public List<BookDto> books(@Argument String search) {
        System.out.println("\n🔥 [LOG 1] Method books() di Controller dipanggil!");
        return bookService.getBooks(search);
    }

    @QueryMapping
    public BookDto book(@Argument Long id) {
        return bookService.getBookById(id);
    }

    @QueryMapping
    public List<AuthorDto> authors() {
        return authorService.getAllAuthors();
    }

    @QueryMapping
    public AuthorDto author(@Argument Long id) {
        return authorService.getAuthorById(id);
    }

    // ==========================================
    // 2. SCHEMA MAPPINGS (Relasi Antar DTO)
    // ==========================================

    // Mengambil DTO Author untuk DTO Book
    // HANYA dipanggil jika client mencentang field "author"
    @SchemaMapping(typeName = "Book", field = "author")
    public AuthorDto author(BookDto book) {
        System.out.println("✨ [LOG 2] @SchemaMapping author() DIPANGGIL untuk buku: " + book.title() + " (Author ID: " + book.authorId() + ")");
        if (book.authorId() == null) return null;
        return authorService.getAuthorById(book.authorId());
    }

    // Mengambil daftar DTO Book untuk DTO Author
    // HANYA dipanggil jika client mencentang field "books"
    @SchemaMapping(typeName = "Author", field = "books")
    public List<BookDto> books(AuthorDto author) {
        return bookService.getBooksByAuthorId(author.id());
    }

    // ==========================================
    // 3. MUTATION MAPPINGS (CRUD via Service)
    // ==========================================

    @MutationMapping
    public AuthorDto createAuthor(@Argument String name) {
        return authorService.createAuthor(name);
    }

    @MutationMapping
    public Boolean deleteAuthor(@Argument Long id) {
        return authorService.deleteAuthor(id);
    }

    @MutationMapping
    public BookDto createBook(@Argument String title, @Argument int year, @Argument Long authorId) {
        return bookService.createBook(title, year, authorId);
    }

    @MutationMapping
    public BookDto updateBook(@Argument Long id, @Argument String title, @Argument Integer year) {
        return bookService.updateBook(id, title, year);
    }

    @MutationMapping
    public Boolean deleteBook(@Argument Long id) {
        return bookService.deleteBook(id);
    }
}
