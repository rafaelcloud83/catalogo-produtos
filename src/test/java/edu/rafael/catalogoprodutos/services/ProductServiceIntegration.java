package edu.rafael.catalogoprodutos.services;

import edu.rafael.catalogoprodutos.repositories.ProductRepository;
import edu.rafael.catalogoprodutos.services.exceptions.EntitiesNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class ProductServiceIntegration {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    private long existingId;
    private long nonExistingId;
    private long countTotalProducts;

    @BeforeEach
    void setUp() throws Exception{
        existingId = 1L;
        nonExistingId = 100L;
        countTotalProducts = 25L;
    }

    @Test
    void deleteShouldDeleteResourceWhenIdExists(){
        productService.delete(existingId);

        Assertions.assertEquals(countTotalProducts - 1, productRepository.count());
    }

    @Test
    void deleteShouldThrowEntitiesNotFoundExceptionWhenIdDoesNotExist(){
        Assertions.assertThrows(EntitiesNotFoundException.class, () -> {
            productService.delete(nonExistingId);
        });
    }
}
