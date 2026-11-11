package com.example.graphqlpractice.repository;

import com.example.graphqlpractice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Cari buku berdasarkan judul
    List<Book> findByTitleContainingIgnoreCase(String title);

    // Cari buku berdasarkan authorId (pengganti @OneToMany)
    List<Book> findByAuthorId(Long authorId);
}
