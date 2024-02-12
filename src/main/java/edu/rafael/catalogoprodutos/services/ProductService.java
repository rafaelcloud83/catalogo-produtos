package edu.rafael.catalogoprodutos.services;

import edu.rafael.catalogoprodutos.dto.CategoryDto;
import edu.rafael.catalogoprodutos.dto.ProductDto;
import edu.rafael.catalogoprodutos.entities.Category;
import edu.rafael.catalogoprodutos.entities.Product;
import edu.rafael.catalogoprodutos.repositories.CategoryRepository;
import edu.rafael.catalogoprodutos.repositories.ProductRepository;
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
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public Page<ProductDto> findAllPaged(Pageable pageable){
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(x -> new ProductDto(x));
    }

    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        Optional<Product> optional = productRepository.findById(id);
        Product product = optional.orElseThrow(() -> new EntitiesNotFoundException("Entidade não encontrada!!!"));
        return new ProductDto(product, product.getCategories());
    }

    @Transactional
    public ProductDto save(ProductDto productDto) {
        Product product = new Product();
        copyDtoToEntity(productDto, product);
        return new ProductDto(productRepository.save(product));
    }

    @Transactional
    public ProductDto update(Long id, ProductDto productDto) {
        try {
            Product product = productRepository.getReferenceById(id);
            copyDtoToEntity(productDto, product);
            return new ProductDto(productRepository.save(product));
        } catch (EntityNotFoundException e){
            throw new EntitiesNotFoundException("Id " + id + " não existe!!!");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!productRepository.existsById(id)){
            throw new EntitiesNotFoundException("Id " + id + " não existe!!!");
        }
        try {
            productRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial, esse produto não pode ser deletada!!!");
        }
    }

    private void copyDtoToEntity(ProductDto productDto, Product product) {
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImgUrl(productDto.getImgUrl());
        product.setDate(productDto.getDate());

        product.getCategories().clear();
        for (CategoryDto categoryDto : productDto.getCategories()){
            Category category = categoryRepository.getReferenceById(categoryDto.getId());
            product.getCategories().add(category);
        }
    }
}
