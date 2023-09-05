package com.library.libraryManager.repository;


import com.library.libraryManager.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BooksRepository extends JpaRepository<Books,Integer> {

    @Query("SELECT b FROM Books b WHERE b.author.id = :authorId")
    List<Books> findAllAuthorID(@Param("authorId") int authorId);


}
