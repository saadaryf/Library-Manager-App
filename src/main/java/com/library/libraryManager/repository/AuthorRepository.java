package com.library.libraryManager.repository;


import com.library.libraryManager.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}

