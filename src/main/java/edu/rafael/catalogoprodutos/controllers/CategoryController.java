package edu.rafael.catalogoprodutos.controllers;

import edu.rafael.catalogoprodutos.dto.CategoryDto;
import edu.rafael.catalogoprodutos.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping()
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        categoryDto = categoryService.save(categoryDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(categoryDto.getId()).toUri();
        return ResponseEntity.created(uri).body(categoryDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto){
        categoryDto = categoryService.update(id, categoryDto);
        return ResponseEntity.ok().body(categoryDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDto> deleteCategory(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
