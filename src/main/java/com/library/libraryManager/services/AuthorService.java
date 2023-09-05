package com.library.libraryManager.services;

import com.library.libraryManager.model.Author;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    @Transactional
    public void createAuthor(Author author);
    @Transactional
    public List<Author> getAuthors();
    @Transactional
    public Author updateAuthor(Author author);
    @Transactional
    public void deleteAuthor(Integer id);

    @Transactional
    public Author getAuthorByID(Integer id);

}
