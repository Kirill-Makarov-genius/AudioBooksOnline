package com.example.AudioBooksOnline.repositories;

import com.example.AudioBooksOnline.entities.Book;
import com.example.AudioBooksOnline.entities.Review;
import com.example.AudioBooksOnline.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Find all reviews by a specific book
    List<Review> findByBook(Book book);

    // Find all reviews written by a specific user
    List<Review> findByUser(User user);

    // Check if user already reviewed a book
    boolean existsByBookAndUser(Book book, User user);
}