package com.ProductsCollaboration.Collaboration.Products.Service;

import com.ProductsCollaboration.Collaboration.Products.DTO.ProductsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    ResponseEntity<?> insertProducts(ProductsDTO productsDTO);

    ResponseEntity<?> getProducts(Long sid);
}
