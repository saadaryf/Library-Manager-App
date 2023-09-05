package com.library.libraryManager.requests;

import com.library.libraryManager.model.Books;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;


import java.util.List;

@Data
public class AuthorRequest {
    private int id;
    private String name;

  //  @NotBlank(message = "Nationality can't be Empty or Null")
    @NotBlank(message = "nationality can't be empty ")
    private String nationality;
    private List<BooksRequest> bookList;

}

