package com.example.AudioBooksOnline.controllers;


import com.example.AudioBooksOnline.entities.Author;
import com.example.AudioBooksOnline.entities.Book;
import com.example.AudioBooksOnline.enums.BookGenre;
import com.example.AudioBooksOnline.services.BookService;
import com.example.AudioBooksOnline.services.FileSystemStorageService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/audio-books")
public class AudioBookController {

    private final FileSystemStorageService fileSystemStorageService;
    private final BookService bookService;

    public AudioBookController(BookService bookService,
        FileSystemStorageService fileSystemStorageService){
            this.fileSystemStorageService = fileSystemStorageService;
            this.bookService = bookService;
    }

    @GetMapping("")
    public String homePage(Model model){
        model.addAttribute("books", bookService.getAllBooks());
        return "index";
    }

    @GetMapping("/{bookId}")
    public String bookPage(@PathVariable Long bookId, Model model){
        Book bookOne = bookService.getBookById(bookId);
        model.addAttribute("book", bookOne);
        return "book";
    }

    @GetMapping("/admin/books/new")
    public String formNewBook(Model model){
        List<Author> authors = bookService.getAllAuthors();
        model.addAttribute("authors", authors);
        model.addAttribute("genres", BookGenre.values());
        return "admin/book-form";
    }

    @PostMapping("/admin/books/new")
    public String storeNewBook(@RequestParam String bookName,
        @RequestParam Long authorId,
        @RequestParam BookGenre bookGenre,
        @RequestParam String description,
        @RequestParam MultipartFile bookCover,
        Model model){
        
        Book newBook = new Book();
        newBook.setBookName(bookName);
        newBook.setAuthor(bookService.getAuthorById(authorId));
        newBook.setBookGenre(bookGenre);
        newBook.setDescription(description);
        newBook.setBookCover(fileSystemStorageService.store(bookCover));
        
        System.out.println(newBook);
        return "redirect:/audio-books";
    }

}
