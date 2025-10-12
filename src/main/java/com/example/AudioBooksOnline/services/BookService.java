package com.example.AudioBooksOnline.services;


import com.example.AudioBooksOnline.entities.Author;
import com.example.AudioBooksOnline.entities.Book;
import com.example.AudioBooksOnline.repositories.AuthorRepository;
import com.example.AudioBooksOnline.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService {

    @Autowired
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(Long id){
        return bookRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Can't find a book by this id"));
    }

    public void storeBook(Book book){
        bookRepository.save(book);
    }

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id){
        return authorRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Can't find an author by this id"));
    }

    public void storeAuthor(Author author){
        authorRepository.save(author);
    }

}