package com.example.hw.endpointCategory;

import com.example.hw.domain.Category;
import com.example.hw.repostories.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CategoryEndpoint {

    private CategoryRepository categoryRepository;

    public CategoryEndpoint(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("api/categories")
    public List<Category> findAllCategories(){
        return (List<Category>) categoryRepository.findAll();
    }

    @GetMapping("api/category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") Long categoryId){
        Category category = categoryRepository.
                findById(categoryId).orElse(null);
        return ResponseEntity.ok().body(category);
    }

    @PostMapping("api/category")
    public Map<String ,Boolean> createCategory(@Valid @RequestBody Category category){
        Map<String,Boolean> response = new HashMap<>();
        try{
            categoryRepository.save(category);
            response.put("Success:",Boolean.TRUE);
        }
        catch (Exception e){
            response.put("Success:", Boolean.FALSE);
        }
        return response;
    }

    @DeleteMapping("api/category/{id}")
    public Map<String,Boolean> deleteCategory(@PathVariable(value = "id") Long categoryId){
        Category category = categoryRepository.findById(categoryId).orElse(null);
        Map<String, Boolean> response = new HashMap<>();
        if (category != null){
            categoryRepository.delete(category);
            response.put("deleted",Boolean.TRUE);
        }
        else
            response.put("category not found",Boolean.FALSE);
        return response;
    }
}
