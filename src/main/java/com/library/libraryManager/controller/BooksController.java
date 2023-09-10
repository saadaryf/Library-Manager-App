// books controller
package com.library.libraryManager.controller;




import com.library.libraryManager.mapper.BooksMapper;
import com.library.libraryManager.model.Author;
import com.library.libraryManager.model.Books;
import com.library.libraryManager.requests.BooksRequest;
import com.library.libraryManager.response.BooksResponse;
import com.library.libraryManager.services.AuthorService;
import com.library.libraryManager.services.BooksService;
import com.library.libraryManager.services.impl.AuthorServiceImpl;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import com.library.libraryManager.exceptions.errors.ErrorResponse;

@Controller
@RequestMapping("/books")
public class BooksController {

    @Autowired
    BooksService booksService;
    @Autowired
    AuthorService authorService;
    @Autowired
    BooksMapper booksMapper;

    @PostMapping("/save")
    public ResponseEntity<?> create(@ModelAttribute BooksRequest booksRequest){
        Author author =authorService.getAuthorByID(booksRequest.getAuthorID());
        if (author==null){
            ErrorResponse errorResponse=new ErrorResponse("Author with this ID NOT Found: ","Add Author First!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
        Books books=booksMapper.mapToEntity(booksRequest);
        booksService.addBook(books);
        return ResponseEntity.ok("Book Saved Successfully");
    }


   /* @GetMapping("/view")
    public List<BooksResponse> getBooks(){
        List<Books> booksList=booksService.getBooks();
        List<BooksResponse> booksResponses=booksList.stream()
                .map(books -> booksMapper.maptoDTO(books))
                .collect(Collectors.toList());
        return booksResponses;
    }*/
   @GetMapping("/view")
   public String getBooks(Model model){
       List<Books> booksList=booksService.getBooks();
       List<BooksResponse> booksResponses=booksList.stream()
               .map(books -> booksMapper.maptoDTO(books))
               .collect(Collectors.toList());
       model.addAttribute("booksResponses",booksResponses);
       return "Books/view";
   }


   /* @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<String> update(@RequestBody BooksRequest booksRequest){
         Books book=booksMapper.mapToEntity(booksRequest);
         booksService.updateBook(book);
        return ResponseEntity.ok("Book updated successfully");
    }*/
   @RequestMapping(value = "/update", method = {RequestMethod.PUT,RequestMethod.POST})
   public ResponseEntity<String> update(@ModelAttribute BooksRequest booksRequest){
       Books book=booksMapper.mapToEntity(booksRequest);
       booksService.updateBook(book);
       return ResponseEntity.ok("Book updated successfully");
   }

    /*@DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody BooksRequest booksRequest){
        Books book=booksMapper.mapToEntity(booksRequest);
        booksService.deleteBook(book.getId());
        return ResponseEntity.ok("Book deleted successfully");
    }*/

    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<String> delete(@RequestParam("bookId") Integer bookId){
        booksService.deleteBook(bookId);
        return ResponseEntity.ok("Book deleted successfully");
    }

}
