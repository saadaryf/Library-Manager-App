package com.library.libraryManager.requests;

import com.library.libraryManager.model.Author;
import lombok.Data;

@Data
public class BooksRequest {
    private int id;
    private String title;
    private int year;
    private int authorID;
}
