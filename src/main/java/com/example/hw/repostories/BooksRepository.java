package com.example.hw.repostories;

import com.example.hw.domain.Author;
import com.example.hw.domain.Books;
import com.example.hw.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long
        > {

    @Query ("select b from Books b where name like %?1%")
    List<Books> getBooksByName(String name, Optional<String> category_name, Optional<String> author_name);
}
