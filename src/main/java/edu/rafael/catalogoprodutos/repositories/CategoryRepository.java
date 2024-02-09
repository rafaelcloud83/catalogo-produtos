package edu.rafael.catalogoprodutos.repositories;

import edu.rafael.catalogoprodutos.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
