// books controller
package com.library.libraryManager.controller;




import com.library.libraryManager.mapper.BooksMapper;
import com.library.libraryManager.model.Author;
import com.library.libraryManager.model.Books;
import com.library.libraryManager.requests.BooksRequest;
import com.library.libraryManager.response.BooksResponse;
import com.library.libraryManager.services.BooksService;
import com.library.libraryManager.services.impl.AuthorServiceImpl;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    BooksService booksService;
    @Autowired
    BooksMapper booksMapper;

    @PostMapping("/save")
    public ResponseEntity<String> create(@RequestBody BooksRequest booksRequest){
        Books books=booksMapper.mapToEntity(booksRequest);
        booksService.addBook(books);
        return ResponseEntity.ok("Book Saved Successfully");
    }

    @GetMapping("/view")
    public List<BooksResponse> getBooks(){
        List<Books> booksList=booksService.getBooks();
        List<BooksResponse> booksResponses=booksList.stream()
                .map(books -> booksMapper.maptoDTO(books))
                .collect(Collectors.toList());
        return booksResponses;
    }


    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<String> update(@RequestBody BooksRequest booksRequest){
         Books book=booksMapper.mapToEntity(booksRequest);
         booksService.updateBook(book);
        return ResponseEntity.ok("Book updated successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody BooksRequest booksRequest){
        Books book=booksMapper.mapToEntity(booksRequest);
        booksService.deleteBook(book.getId());
        return ResponseEntity.ok("Book deleted successfully");
    }

}
