package com.library.libraryManager.response;

import lombok.Data;

@Data
public class BooksResponse {

    private int id;
    private String title;
    private int year;
    private int authorID;
}
