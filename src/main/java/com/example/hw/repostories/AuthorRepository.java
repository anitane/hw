package com.example.hw.repostories;

import com.example.hw.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long> {
}
