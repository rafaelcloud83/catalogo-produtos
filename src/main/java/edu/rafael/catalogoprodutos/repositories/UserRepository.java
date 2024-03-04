package edu.rafael.catalogoprodutos.repositories;

import edu.rafael.catalogoprodutos.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
