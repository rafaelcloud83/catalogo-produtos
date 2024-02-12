package edu.rafael.catalogoprodutos.services;

import edu.rafael.catalogoprodutos.dto.CategoryDto;
import edu.rafael.catalogoprodutos.entities.Category;
import edu.rafael.catalogoprodutos.repositories.CategoryRepository;
import edu.rafael.catalogoprodutos.services.exceptions.DatabaseException;
import edu.rafael.catalogoprodutos.services.exceptions.EntitiesNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public Page<CategoryDto> findAllPaged(Pageable pageable){
        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories.map(x -> new CategoryDto(x));
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

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!categoryRepository.existsById(id)){
            throw new EntitiesNotFoundException("Id " + id + " não existe!!!");
        }
        try {
            categoryRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial, essa categoria não pode ser deletada!!!");
        }
    }
}
