package com.example.AudioBooksOnline.controllers;


import com.example.AudioBooksOnline.entities.Book;
import com.example.AudioBooksOnline.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/audio-books")
public class AudioBookController {


    private final BookService bookService;

    public AudioBookController(BookService bookService){
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

}
