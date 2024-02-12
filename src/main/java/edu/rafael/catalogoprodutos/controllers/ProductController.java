package edu.rafael.catalogoprodutos.controllers;

import edu.rafael.catalogoprodutos.dto.ProductDto;
import edu.rafael.catalogoprodutos.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<Page<ProductDto>> findAll(Pageable pageable){
        Page<ProductDto> products = productService.findAllPaged(pageable);
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> finById(@PathVariable Long id){
        ProductDto productDto = productService.findById(id);
        return ResponseEntity.ok().body(productDto);
    }

    @PostMapping()
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        productDto = productService.save(productDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(productDto.getId()).toUri();
        return ResponseEntity.created(uri).body(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto){
        productDto = productService.update(id, productDto);
        return ResponseEntity.ok().body(productDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
