package com.library.libraryManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String showMainPage(){
        return "mainPage";
    }
    @GetMapping("authorForm")
    public String openAuthorForm(){
        return "/Author/index";
    }
    @GetMapping("booksForm")
    public String openBooksForm(){
        return "/Books/index";
    }

    @GetMapping("deleteAuthor")
    public String deleteAuthor(){
        return "/Author/delete";
    }
    @GetMapping("deleteBook")
    public String deleteBook(){
        return "/Books/delete";
    }
    @GetMapping("updateAuthor")

    public String updateAuthor(){
        return "/Author/update";
    }
    @GetMapping("updateBook")
    public String updateBook(){
        return "/Books/update";
    }
}
