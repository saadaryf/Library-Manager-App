package com.library.libraryManager.response;

import com.library.libraryManager.requests.BooksRequest;
import lombok.Data;

import java.util.List;

@Data
public class AuthorResponse {
    private int id;
    private String name;
    private String nationality;
    private List<BooksRequest> bookList;
}
