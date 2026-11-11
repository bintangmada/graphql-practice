package com.example.graphqlpractice.service;

import com.example.graphqlpractice.dto.AuthorDto;
import com.example.graphqlpractice.exception.ResourceNotFoundException;
import com.example.graphqlpractice.dto.BookDto;
import com.example.graphqlpractice.model.Author;
import com.example.graphqlpractice.repository.AuthorRepository;
import com.example.graphqlpractice.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional(readOnly = true)
    public List<AuthorDto> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(AuthorDto::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public AuthorDto getAuthorById(Long id) {
        return authorRepository.findById(id)
                .map(AuthorDto::fromEntity)
                .orElse(null);
    }

    public AuthorDto createAuthor(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nama penulis tidak boleh kosong!");
        }
        Author author = new Author(name);
        Author saved = authorRepository.save(author);
        return AuthorDto.fromEntity(saved);
    }

    public boolean deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Penulis dengan ID " + id + " tidak ditemukan!");
        }
        authorRepository.deleteById(id);
        return true;
    }
}

