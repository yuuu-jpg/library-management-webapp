package com.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 50)
    private String genre;

    private Integer publishedYear;

    @Column(length = 20)
    private String isbn;
}
