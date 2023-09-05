package com.library.libraryManager.requests;

import com.library.libraryManager.model.Books;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class AuthorRequest {
    private int id;
    private String name;

    @NotNull(message = "\nNationality can't be NULL") @NotEmpty(message = "\nNationality can't be EMPTY!")
    private String nationality;
    private List<BooksRequest> bookList;

}

