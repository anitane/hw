package com.example.hw.endpoint.books;

import com.example.hw.domain.Books;
import com.example.hw.repostories.AuthorRepository;
import com.example.hw.repostories.BooksRepository;
import com.example.hw.repostories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
public class BooksEndpoint {

    private BooksRepository booksRepository;

    @Autowired
    public BooksEndpoint(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @GetMapping("api/books")
    public List<Books> findAllBooks(){
        return booksRepository.findAll();
    }

    @GetMapping("api/book/{id}")
    public ResponseEntity<Books> getBookById(@PathVariable(value = "id") Long bookId) {
        Books book = booksRepository.
                findById(bookId).orElse(null);
        return ResponseEntity.ok().body(book);
    }

    @PostMapping("api/book")
    public Map<String,Boolean> createBook(@Valid @RequestBody Books book){
        Map<String,Boolean> response = new HashMap<>();
        try{
            booksRepository.save(book);
            response.put("Success:" , Boolean.TRUE);
        }
        catch (Exception e){
            response.put("Success:", Boolean.FALSE);
        }
        return response;
    }

    @DeleteMapping("api/book/{id}")
    public Map<String,Boolean> deleteBook (@PathVariable(value = "id") Long bookId){
        Books book = booksRepository.findById(bookId).orElse(null);
        Map<String, Boolean> response = new HashMap<>();
        if (book!=null) {
            booksRepository.delete(book);
            response.put("deleted", Boolean.TRUE);
        }
        else {
            response.put("book not found", Boolean.FALSE);
        }
        return response;
    }

    @GetMapping("api/books/search")
    public List<Books> findByFilters(@RequestParam String filter,
                                     @RequestParam (value = "sorted" , defaultValue = "false" ,required = false) Boolean sorted,
                                     @RequestParam Optional<String> category_name,
                                     @RequestParam Optional<String> author_name){
        List<Books> booksList = booksRepository.getBooksByName(filter,category_name,author_name);
        if (sorted){
            Collections.sort(booksList);
        }
        return booksList;
    }
}
