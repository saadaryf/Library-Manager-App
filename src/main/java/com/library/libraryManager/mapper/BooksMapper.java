//books mapper
package com.library.libraryManager.mapper;

import com.library.libraryManager.response.BooksResponse;
import com.library.libraryManager.requests.BooksRequest;
import com.library.libraryManager.model.Author;
import com.library.libraryManager.model.Books;
import com.library.libraryManager.services.AuthorService;
import com.library.libraryManager.services.impl.AuthorServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class BooksMapper{
    @Autowired
    AuthorService authorService;
    public BooksResponse maptoDTO(Books book){
        BooksResponse booksDTO=new BooksResponse();
        booksDTO.setId(book.getId());
        booksDTO.setTitle(book.getTitle());
        booksDTO.setYear(book.getYear());
        booksDTO.setAuthorID(book.getAuthor().getId());
        return booksDTO;
    }
    private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);

    public Books mapToEntity(BooksRequest booksRequest){
        Books books=new Books();
        books.setId(booksRequest.getId());
        books.setYear(booksRequest.getYear());
        books.setTitle(booksRequest.getTitle());
    //    logger.info("\nAuthor id == "+booksDTO.getAuthorID());
        Author author=authorService.getAuthorByID(booksRequest.getAuthorID());
        if (author == null) {
            logger.info("\nAuthor is null");
        }
        books.setAuthor(author);
        return books;
    }


}
