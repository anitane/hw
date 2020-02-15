package com.example.hw.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Id;


@Entity
@Table(name = "categories")

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @Column (name = "name" , nullable = false)
    private String name;

    @Column  (name = "description" , nullable = false)
    private String description;

    @ManyToMany(mappedBy = "categorybooks")
    Set<Books> books;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonIgnore
    public Set<Books> getBooks() {
        return books;
    }

    public void setBooks(Set<Books> books) {
        this.books = books;
    }
}
