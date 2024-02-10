package edu.rafael.catalogoprodutos.services;

import edu.rafael.catalogoprodutos.dto.CategoryDto;
import edu.rafael.catalogoprodutos.entities.Category;
import edu.rafael.catalogoprodutos.repositories.CategoryRepository;
import edu.rafael.catalogoprodutos.services.exceptions.EntitiesNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<CategoryDto> findAll(){
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(x -> new CategoryDto(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryDto findById(Long id) {
        Optional<Category> optional = categoryRepository.findById(id);
        Category category = optional.orElseThrow(() -> new EntitiesNotFoundException("Entidade não encontrada!!!"));
        return new CategoryDto(category);
    }

    @Transactional
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        return new CategoryDto(categoryRepository.save(category));
    }

    @Transactional
    public CategoryDto update(Long id, CategoryDto categoryDto) {
        try {
            Category category = categoryRepository.getReferenceById(id);
            category.setName(categoryDto.getName());
            return new CategoryDto(categoryRepository.save(category));
        } catch (EntityNotFoundException e){
            throw new EntitiesNotFoundException("Id " + id + " não existe!!!");
        }
    }
}
