package com.ProductsCollaboration.Collaboration.Products.DAO;

import com.ProductsCollaboration.Collaboration.Products.Entity.Products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Products,Long> {
}
