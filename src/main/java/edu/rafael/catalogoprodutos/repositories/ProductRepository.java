package edu.rafael.catalogoprodutos.repositories;

import edu.rafael.catalogoprodutos.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
