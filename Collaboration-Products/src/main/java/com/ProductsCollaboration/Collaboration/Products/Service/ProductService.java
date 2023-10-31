package com.ProductsCollaboration.Collaboration.Products.Service;

import com.ProductsCollaboration.Collaboration.Products.DTO.OrderProductDTO;
import com.ProductsCollaboration.Collaboration.Products.DTO.ProductsDTO;
import com.ProductsCollaboration.Collaboration.Products.DTO.UpdateProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    ResponseEntity<?> insertProducts(ProductsDTO productsDTO);

    ResponseEntity<?> getProducts(Long sid);


    ResponseEntity<?> orderProducts(OrderProductDTO orderProduct);

    ResponseEntity<?> updateProduct(Long pid, UpdateProductDTO updateProduct);
}
