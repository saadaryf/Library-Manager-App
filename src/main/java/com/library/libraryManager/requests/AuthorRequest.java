package com.library.libraryManager.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class AuthorRequest {
    private int id;
    @Size(min = 5, message = "minimum length for name is 5")
    private String name;

    @NotBlank(message = "nationality can't be empty ")
    private String nationality;
 //   private List<BooksRequest> bookList;

}

