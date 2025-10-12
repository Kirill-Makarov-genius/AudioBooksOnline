package com.example.AudioBooksOnline.controllers;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Map;

@ControllerAdvice
public class RouteController {

    @ModelAttribute("routes")
    public Map<String, String> routes(){
        return Map.of(
                "home", "/audio-books",
                "books", "/audio-books/books/",
                "admin", "/audio-books/admin/",
                "adminBooks", "/audio-books/admin/books",
                "adminAuthors", "/audio-books/admin/authors",
                "files", "/files/"
        );
    }

}
