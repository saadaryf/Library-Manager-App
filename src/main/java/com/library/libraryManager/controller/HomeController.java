package com.library.libraryManager.controller;

import com.library.libraryManager.mapper.AuthorMapper;
import com.library.libraryManager.mapper.BooksMapper;
import com.library.libraryManager.model.Author;
import com.library.libraryManager.model.Books;
import com.library.libraryManager.response.AuthorResponse;
import com.library.libraryManager.response.BooksResponse;
import com.library.libraryManager.services.AuthorService;
import com.library.libraryManager.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    @Autowired
    AuthorService authorService;
    @Autowired
    BooksService booksService;
    @Autowired
    AuthorMapper authorMapper;
    @Autowired
    BooksMapper booksMapper;

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

    @GetMapping("/viewAll")
    public String viewAuthor(Model model){
        List<Author> authorList=authorService.getAuthors();
        List<AuthorResponse> authorResponses= authorList.stream()
                .map(author->authorMapper.mapToDto(author))
                .collect(Collectors.toList());
        model.addAttribute("authorResponses",authorResponses);
        return "/viewAll";
    }
   /* @GetMapping("viewAll")
    public String viewAll(Model model){
        List<AuthorResponse> authorResponses=
        return "/viewAll";
    }*/
}
