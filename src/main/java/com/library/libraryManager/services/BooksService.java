package com.library.libraryManager.services;

import com.library.libraryManager.model.Author;
import com.library.libraryManager.model.Books;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BooksService {
    @Transactional
    public void addBook(Books book);
    @Transactional
    public List<Books> getBooks();
    @Transactional
    public Books updateBook(Books book);
    @Transactional
    public Books getBookByID(Integer id);
    @Transactional
    public void deleteBook(Integer id);
}
