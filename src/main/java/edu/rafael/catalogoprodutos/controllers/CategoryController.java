package edu.rafael.catalogoprodutos.controllers;

import edu.rafael.catalogoprodutos.entities.Category;
import edu.rafael.catalogoprodutos.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public ResponseEntity<List<Category>> findAll(){
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok().body(categories);
    }
}
