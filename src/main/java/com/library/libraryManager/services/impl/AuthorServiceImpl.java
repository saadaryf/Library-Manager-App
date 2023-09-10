package com.library.libraryManager.services.impl;

import com.library.libraryManager.model.Author;
import com.library.libraryManager.model.Books;
import com.library.libraryManager.repository.AuthorRepository;
import com.library.libraryManager.repository.BooksRepository;
import com.library.libraryManager.services.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BooksRepository booksRepository;
    @Override
    public void createAuthor(Author author) {
     /*   List<Books> books=author.getBookList().stream()
                .map(booksDTO -> {Books books1 =new Books();
                    books1.setTitle(booksDTO.getTitle());
                    books1.setYear(booksDTO.getYear());
                    books1.setId(booksDTO.getId());
                    books1.setAuthor(author);
                    return books1;
                })
                .collect(Collectors.toList());
*/
        authorRepository.save(author);

     //   booksRepository.saveAll(books);


    }

    @Override
    public List<Author> getAuthors() {

        return authorRepository.findAll();

    }


    @Override
    public Author updateAuthor(Author author) {

        Optional<Author> foundAuthorOptional=authorRepository.findById(author.getId());
        if(foundAuthorOptional.isPresent()){
            Author foundAuthor=foundAuthorOptional.get();
            foundAuthor.setName(author.getName());
            foundAuthor.setNationality(author.getNationality());
            return authorRepository.save(foundAuthor);
        }
        else {
            logger.warn("No Author Present to Delete with Id: {}", author.getId());
            return null;
        }
    }

    @Override
    public void deleteAuthor(Integer id) {
        Optional<Author> foundAuthorOptional = authorRepository.findById(id);
        foundAuthorOptional.ifPresent(author -> { authorRepository.delete(author);
        logger.info("Author with Id {} deleted successfully.", id);
        });

        if (!foundAuthorOptional.isPresent()) {
            logger.warn("No Author Present to Delete with Id: {}", id);
        }
    }

    @Override
    public Author getAuthorByID(Integer id) {
        Optional<Author> foundAuthor=authorRepository.findById(id);
        return foundAuthor.orElse(null);
    }


}
