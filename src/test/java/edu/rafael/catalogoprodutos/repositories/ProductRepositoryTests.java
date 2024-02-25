package edu.rafael.catalogoprodutos.repositories;

import edu.rafael.catalogoprodutos.entities.Product;
import edu.rafael.catalogoprodutos.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
class ProductRepositoryTests {
    @Autowired
    private ProductRepository productRepository;

    private long existingId;
    private long nonExistingId;
    private long countTotalProducts;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 100L;
        countTotalProducts = 25L;
    }

    @Test
    void saveShouldPersistWithAutoincrementWhenIdIsNull(){
        Product product = Factory.createProduct();
        product.setId(null);

        product = productRepository.save(product);

        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(countTotalProducts+1, product.getId());
    }

    @Test
    void findByIdShouldReturnNonEmptyOptionalWhenIdExists(){
        Optional<Product> optional = productRepository.findById(existingId);

        Assertions.assertTrue(optional.isPresent());
    }

    @Test
    void findByIdShouldReturnNonEmptyOptionalWhenIdDoesNotExist(){
        Optional<Product> optional = productRepository.findById(nonExistingId);

        Assertions.assertTrue(optional.isEmpty());
    }
}
