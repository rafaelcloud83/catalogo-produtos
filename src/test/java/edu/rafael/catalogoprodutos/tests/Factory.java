package edu.rafael.catalogoprodutos.tests;

import edu.rafael.catalogoprodutos.dto.ProductDto;
import edu.rafael.catalogoprodutos.entities.Category;
import edu.rafael.catalogoprodutos.entities.Product;

import java.time.Instant;

public class Factory {

    public static Product createProduct(){
        Product product = new Product(1L,"Celular Samsung s24", "Celular de ultima geração",4000.00,"https://images.samsung.com/is/image/samsung/p6pim/levant/feature/164573622/levant-feature-simple-yet-stylish-536625426?$FB_TYPE_A_JPG$", Instant.now());
        product.getCategories().add(createCategory());
        return product;
    }

    public static ProductDto createProductDto(){
        Product product = createProduct();
        return new ProductDto(product, product.getCategories());
    }

    public static Category createCategory(){
        return new Category(2L,"Eletrônicos");
    }
}
