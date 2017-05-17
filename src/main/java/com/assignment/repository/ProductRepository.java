package com.assignment.repository;

import com.assignment.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Admin on 17/4/2560.
 */
public interface ProductRepository extends CrudRepository<Product,Long> {
    Product findById(long id);
    Product findByName(String name);
    List<Product> findByNameIgnoreCaseContainingOrderByName(String name);
    List<Product> findByNameIgnoreCase(String searchText);
}
