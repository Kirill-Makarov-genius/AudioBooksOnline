package com.example.AudioBooksOnline.repositories;

import com.example.AudioBooksOnline.entities.Author;
import com.example.AudioBooksOnline.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Find all books by specific author
    List<Book> findByAuthor(Author author);


}
