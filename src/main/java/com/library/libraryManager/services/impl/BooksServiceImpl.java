package com.library.libraryManager.services.impl;

import com.library.libraryManager.model.Author;
import com.library.libraryManager.model.Books;
import com.library.libraryManager.repository.AuthorRepository;
import com.library.libraryManager.repository.BooksRepository;
import com.library.libraryManager.services.BooksService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BooksServiceImpl implements BooksService {
    private static final Logger logger = LoggerFactory.getLogger(BooksServiceImpl.class);


    @Autowired
    BooksRepository booksRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Override
    public void addBook(Books book) {
        Optional<Author> authorOptional = authorRepository.findById(book.getAuthor().getId());
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            book.setAuthor(author);
        } else {
            throw new IllegalArgumentException("No valid author found for the provided author ID");
        }
        booksRepository.save(book);

    }




    @Override
    public List<Books> getBooks() {

        return booksRepository.findAll();
    }

    @Override
    public Books updateBook(Books book) {
        Optional<Books> foundBookOptional = booksRepository.findById(book.getId());
        if (foundBookOptional.isPresent()) {
            Books foundBook = foundBookOptional.get();
            // Update the attributes of the found book with the values from the input book
            foundBook.setTitle(book.getTitle());
            foundBook.setYear(book.getYear());
            foundBook.setAuthor(book.getAuthor());
            return booksRepository.save(foundBook);
        } else {
            logger.warn("No Book Present to Update with Id: {}", book.getId());
            return null;
        }
    }

    @Override
    public Books getBookByID(Integer id) {
        Optional<Books> booksOptional=booksRepository.findById(id);
        return booksOptional.orElse(null);
    }

    @Override
    public void deleteBook(Integer id) {
        Optional<Books> foundBookOptional = booksRepository.findById(id);
        if (foundBookOptional.isPresent()) {
            booksRepository.delete(foundBookOptional.get());
            logger.info("Book with Id {} deleted successfully.", id);
        }
    }
}
