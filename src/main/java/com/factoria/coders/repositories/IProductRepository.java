package com.factoria.coders.repositories;

import com.factoria.coders.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);

//    List<Product> findByNameAndDescriptionContains(String search);
    List<Product> findByDescriptionContains(String search);
//    List<Product> searchByDescriptionAndNameLike(String search);

    List<Product> findByNameContainsOrDescriptionContainsAllIgnoreCase(String name, String description);




}
