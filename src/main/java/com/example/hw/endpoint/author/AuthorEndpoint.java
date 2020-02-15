package com.example.hw.endpoint.author;

import com.example.hw.domain.Author;
import com.example.hw.repostories.AuthorRepository;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AuthorEndpoint {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorEndpoint(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @GetMapping("api/authors")
    public List<Author> findAllAuthors(){
        return (List<Author>) authorRepository.findAll();
    }

    @GetMapping("api/author/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable(value = "id") Long authorId) {
        Author author = authorRepository.
                findById(authorId).orElse(null);
        return ResponseEntity.ok().body(author);
    }

    @PostMapping("api/author")
    public Map<String,Boolean> createAuthor(@Valid @RequestBody Author author){
        Map<String, Boolean> response = new HashMap<>();
        try {
            authorRepository.save(author);
            response.put("Success:", Boolean.TRUE);
        }
        catch (Exception e){
            response.put("Success:", Boolean.FALSE);
        }
        return response;
    }

    @DeleteMapping("api/author/{id}")
    public Map<String,Boolean> deleteAuthor(@PathVariable(value = "id") Long authorId){
        Author author = authorRepository.findById(authorId).orElse(null);
        Map<String, Boolean> response = new HashMap<>();
        if (author!= null){
            authorRepository.delete(author);
            response.put("deleted",Boolean.TRUE);
        }
        else
            response.put("Author not found" , Boolean.FALSE);
        return response;
    }



}
