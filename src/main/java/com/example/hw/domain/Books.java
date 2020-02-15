package com.example.hw.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.Id;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "books")
@EntityListeners(AuditingEntityListener.class)

public class Books implements Comparable<Books>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name" , nullable = false)
    private String name;

    @Column (name = "number_of_pages" , nullable = false)
    private int numberOfPages;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id" , referencedColumnName = "id" )
    private Author author;

    @ManyToMany
    @JoinTable(
            name = "books_category",
            joinColumns = @JoinColumn(name = "books_id"),
            inverseJoinColumns = @JoinColumn (name = "category_id")
    )
    Set<Category> categorybooks;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

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

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Set<Category> getCategorybooks() {
        return categorybooks;
    }

    public void setCategorybooks(Set<Category> categorybooks) {
        this.categorybooks = categorybooks;
    }

    public int compareTo(Books otherbook){
        return (this.getNumberOfPages() - otherbook.getNumberOfPages());
    }
}
