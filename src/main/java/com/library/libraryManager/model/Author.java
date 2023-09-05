package com.library.libraryManager.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Singular;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name="Author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;


    @Column(name="name")
    private String name;


    @Column(name="nationality")
    private String nationality;


    @OneToMany(mappedBy = "author",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Books> bookList;


}
