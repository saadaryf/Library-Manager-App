// author controller
package com.library.libraryManager.controller;

import com.library.libraryManager.requests.AuthorRequest;
import com.library.libraryManager.mapper.AuthorMapper;
import com.library.libraryManager.model.Author;
import com.library.libraryManager.response.AuthorResponse;
import com.library.libraryManager.services.AuthorService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
//import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import com.library.libraryManager.exceptions.errors.ErrorResponse;
@RestController
@RequestMapping("/author")
public class AuthorController {

   // private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);

    @Autowired
    AuthorService authorService;

    @Autowired
    private AuthorMapper authorMapper;

    @PostMapping("/save")
    public ResponseEntity<?> create(@Valid @RequestBody AuthorRequest authorRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            String errorDetails=bindingResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getField()+": "+fieldError.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            ErrorResponse errorResponse=new ErrorResponse("Validation Failed",errorDetails);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }

         Author author=authorMapper.mapToEntity(authorRequest);
         authorService.createAuthor(author);

         return ResponseEntity.ok("Author Saved Successfully");
    }



    @GetMapping("/view")
    public List<AuthorResponse> viewAuthor(){
        List<Author> authorList=authorService.getAuthors();
        List<AuthorResponse> authorResponses= authorList.stream()
                .map(author->authorMapper.mapToDto(author))
                .collect(Collectors.toList());
        return authorResponses;
    }
    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable Integer id){
        Author author=authorService.getAuthorByID(id);
        if(author != null){
            AuthorResponse authorResponse=authorMapper.mapToDto(author);
            return ResponseEntity.ok(authorResponse);
        }else {
            return ResponseEntity.notFound().build();
        }
    }



    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<String> update(@RequestBody AuthorRequest authorRequest){
         Author author=authorMapper.mapToEntity(authorRequest);
         authorService.updateAuthor(author);
         return ResponseEntity.ok("Author updated successfully");
    }

    @DeleteMapping("/deletes")
    public ResponseEntity<String> delete(@RequestBody AuthorRequest authorRequest){
        Author author=authorMapper.mapToEntity(authorRequest);
        authorService.deleteAuthor(author.getId());
        return ResponseEntity.ok("Author deleted successfully");
    }



}
