package com.example.graphqlpractice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int year;

    // Kolom relasi murni sebagai ID biasa (Flat Column)
    // Tanpa anotasi @ManyToOne atau @JoinColumn
    @Column(name = "author_id", nullable = false)
    private Long authorId;

    public Book() {
    }

    public Book(String title, int year, Long authorId) {
        this.title = title;
        this.year = year;
        this.authorId = authorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
