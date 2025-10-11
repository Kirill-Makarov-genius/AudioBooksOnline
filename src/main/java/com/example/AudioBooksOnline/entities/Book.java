package com.example.AudioBooksOnline.entities;


import com.example.AudioBooksOnline.enums.BookGenre;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    private String bookName;
    private String description;
    @Enumerated(EnumType.STRING)
    private BookGenre bookGenre;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Author author;
    private String bookCover;
}
