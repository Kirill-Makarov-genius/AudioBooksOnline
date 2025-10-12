
package com.example.AudioBooksOnline.controllers;


import com.example.AudioBooksOnline.entities.Author;
import com.example.AudioBooksOnline.entities.Book;
import com.example.AudioBooksOnline.enums.BookGenre;
import com.example.AudioBooksOnline.repositories.BookRepository;
import com.example.AudioBooksOnline.services.BookService;
import com.example.AudioBooksOnline.services.FileSystemStorageService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/audio-books")
public class AudioBookController {

    private final FileSystemStorageService fileSystemStorageService;
    private final BookService bookService;

    private final BookRepository bookRepository;

    public AudioBookController(BookService bookService,
                               FileSystemStorageService fileSystemStorageService,
                               BookRepository bookRepository){
            this.fileSystemStorageService = fileSystemStorageService;
            this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @GetMapping("")
    public String homePage(Model model){
        model.addAttribute("books", bookService.getAllBooks());
        return "index";
    }

    @GetMapping("/books/{bookId}")
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
        model.addAttribute("book", new Book());
        model.addAttribute("action", "/new");
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
        bookService.storeBook(newBook);
        
        System.out.println(newBook);

        return "redirect:/audio-books";
    }


    @GetMapping("/admin/books/edit/{bookId}")
    public String formEditBook(@PathVariable long bookId, Model model){
        List<Author> authors = bookService.getAllAuthors();
        model.addAttribute("authors", authors);
        model.addAttribute("genres", BookGenre.values());
        model.addAttribute("action", "/edit/" + bookId);

        Book bookEdit = bookService.getBookById(bookId);
        model.addAttribute("book", bookEdit);

        return "admin/book-form";
    }

    @PostMapping("/admin/books/edit/{bookId}")
    public String editBook(@PathVariable long bookId,
                           @ModelAttribute("book") Book book,
                           @RequestParam("bookCoverFile") MultipartFile bookCover){
        Book existingBook = bookService.getBookById(bookId);
        existingBook.setBookName(book.getBookName());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setBookGenre(book.getBookGenre());
        existingBook.setDescription(book.getDescription());
        existingBook.setBookCover(fileSystemStorageService.store(bookCover));
        bookService.storeBook(existingBook);
        return "redirect:/audio-books/books/" + bookId;

    }


    @GetMapping("/admin/authors/new")
    public String formNewAuthor(Model model){
        return "admin/author-form";
    }

    @PostMapping("/admin/authors/new")
    public String storeNewAuthor(@RequestParam String authorName,
         @RequestParam MultipartFile author_image,
         Model model){
        Author newAuthor = new Author();
        newAuthor.setName(authorName);
        newAuthor.setAuthor_image(fileSystemStorageService.store(author_image));
        bookService.storeAuthor(newAuthor);

        return "redirect:/audio-books";

    }
}
