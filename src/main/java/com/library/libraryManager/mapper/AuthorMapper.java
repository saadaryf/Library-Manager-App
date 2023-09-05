//author mapper
package com.library.libraryManager.mapper;

import com.library.libraryManager.requests.AuthorRequest;
import com.library.libraryManager.requests.BooksRequest;
import com.library.libraryManager.model.Author;
import com.library.libraryManager.model.Books;
import com.library.libraryManager.response.AuthorResponse;
import com.library.libraryManager.response.BooksResponse;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorMapper {


    public AuthorResponse mapToDto(Author author) {
        AuthorResponse dto = new AuthorResponse();
        dto.setId(author.getId());
        dto.setName(author.getName());
        dto.setNationality(author.getNationality());
        List<BooksRequest> booksDTOS= mapBookstoDTO(author.getBookList());
        dto.setBookList(booksDTOS);
        return dto;
    }
    private List<BooksRequest> mapBookstoDTO(List<Books> books){
        return books.stream()
                .map(book ->{
                    BooksRequest booksDTO=new BooksRequest();
                    booksDTO.setId(book.getId());
                    booksDTO.setTitle(book.getTitle());
                    booksDTO.setYear(book.getYear());
                    booksDTO.setAuthorID(book.getAuthor().getId());
                    return booksDTO;
                } )
                .collect(Collectors.toList());

    }

    public Author mapToEntity(AuthorRequest authorRequest){
        Author author=new Author();
        author.setId(authorRequest.getId());
        author.setName(authorRequest.getName());
        author.setNationality(authorRequest.getNationality());
        if(authorRequest.getBookList()!= null) {
            List<Books> booksList = mapBooksToEntity(authorRequest.getBookList());
            author.setBookList(booksList);
        } else {
            author.setBookList(Collections.emptyList());
        }
        return author;
    }
    private List<Books> mapBooksToEntity(List<BooksRequest> booksRequests){
        return booksRequests.stream()
                .map(BooksResponse -> {
                    Books books=new Books();
                    books.setId(BooksResponse.getId());
                    books.setTitle(BooksResponse.getTitle());
                    books.setYear(BooksResponse.getYear());
                    Author author=new Author();
                    author.setId(BooksResponse.getAuthorID());
                    books.setAuthor(author);
                    return books;
                })
                .collect(Collectors.toList());
    }
}

