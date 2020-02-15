package com.example.hw.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Id;


@Entity
@Table (name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id" ,nullable = false)
    private long id;

    @Column (name = "first_name" , nullable = false)
    private String firstName;

    @Column (name = "last_name" , nullable = false)
    private String lastName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonBackReference
    @OneToMany(mappedBy = "author" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Books> book = new ArrayList<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Books> getBook() {
        return book;
    }

    public void setBook(List<Books> book) {
        this.book = book;
    }


}
