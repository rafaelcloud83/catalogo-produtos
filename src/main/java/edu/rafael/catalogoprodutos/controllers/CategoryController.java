package edu.rafael.catalogoprodutos.controllers;

import edu.rafael.catalogoprodutos.dto.CategoryDto;
import edu.rafael.catalogoprodutos.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<List<CategoryDto>> findAll(){
        List<CategoryDto> categories = categoryService.findAll();
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> finById(@PathVariable Long id){
        CategoryDto categoryDto = categoryService.findById(id);
        return ResponseEntity.ok().body(categoryDto);
    }
}
