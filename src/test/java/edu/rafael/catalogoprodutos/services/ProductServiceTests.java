package edu.rafael.catalogoprodutos.services;

import edu.rafael.catalogoprodutos.dto.ProductDto;
import edu.rafael.catalogoprodutos.entities.Category;
import edu.rafael.catalogoprodutos.entities.Product;
import edu.rafael.catalogoprodutos.repositories.CategoryRepository;
import edu.rafael.catalogoprodutos.repositories.ProductRepository;
import edu.rafael.catalogoprodutos.services.exceptions.DatabaseException;
import edu.rafael.catalogoprodutos.services.exceptions.EntitiesNotFoundException;
import edu.rafael.catalogoprodutos.tests.Factory;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    private long existingId;
    private long nonExistingId;
    private long dependentId;
    private PageImpl<Product> page;
    private Product product;
    private Category category;
    private ProductDto prodDto;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 100L;
        dependentId = 3L;
        product = Factory.createProduct();
        page = new PageImpl<>(List.of(product));
        category = Factory.createCategory();
        prodDto = Factory.createProductDto();

        Mockito.when(productRepository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(page);

        Mockito.when(productRepository.save(ArgumentMatchers.any())).thenReturn(product);

        Mockito.when(productRepository.getReferenceById(existingId)).thenReturn(product);
        Mockito.when(productRepository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);
        Mockito.when(categoryRepository.getReferenceById(existingId)).thenReturn(category);
        Mockito.when(categoryRepository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);

        Mockito.when(productRepository.findById(existingId)).thenReturn(Optional.of(product));
        Mockito.when(productRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        Mockito.doThrow(DataIntegrityViolationException.class).when(productRepository).deleteById(dependentId);
        Mockito.when(productRepository.existsById(existingId)).thenReturn(true);
        Mockito.when(productRepository.existsById(nonExistingId)).thenReturn(false);
        Mockito.when(productRepository.existsById(dependentId)).thenReturn(true);
    }

    @Test
    void findByIdShouldReturnProductDtoWhenIdExists(){
        ProductDto productDto = productService.findById(existingId);

        Assertions.assertNotNull(productDto);
    }

    @Test
    void findByIdShouldEntitiesNotFoundExceptionWhenIdDoesNotExist(){
        Assertions.assertThrows(EntitiesNotFoundException.class, () -> {
            productService.findById(nonExistingId);
        });
    }

    @Test
    void updateShouldReturnProductDtoWhenIdExists(){
        ProductDto productDto = productService.update(existingId, prodDto);

        Assertions.assertNotNull(productDto);
    }

    @Test
    void updateShouldEntitiesNotFoundExceptionWhenIdDoesNotExist(){
        Assertions.assertThrows(EntitiesNotFoundException.class, () -> {
            productService.update(nonExistingId, prodDto);
        });
    }

    @Test
    void deleteShouldDoNothingWhenIdExists(){
        Assertions.assertDoesNotThrow(() -> {
            productService.delete(existingId);
        });
    }

    @Test
    void deleteShouldThrowEntitiesNotFoundExceptionWhenIdDoesNotExist(){
        Assertions.assertThrows(EntitiesNotFoundException.class, () -> {
            productService.delete(nonExistingId);
        });
    }

    @Test
    void deleteShouldThrowDatabaseExceptionWhenDependentId(){
        Assertions.assertThrows(DatabaseException.class, () -> {
            productService.delete(dependentId);
        });
    }
}
