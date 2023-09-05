package com.library.libraryManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class LibraryManagerApplication {


	public static void main(String[] args) {
		SpringApplication.run(LibraryManagerApplication.class, args);
	}

}
