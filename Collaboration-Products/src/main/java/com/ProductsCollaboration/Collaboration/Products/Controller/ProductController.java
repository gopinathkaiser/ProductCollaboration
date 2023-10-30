package com.ProductsCollaboration.Collaboration.Products.Controller;

import com.ProductsCollaboration.Collaboration.Products.DTO.OrderProductDTO;
import com.ProductsCollaboration.Collaboration.Products.DTO.ProductsDTO;
import com.ProductsCollaboration.Collaboration.Products.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("addProducts")
    public ResponseEntity<?> insertProducts(@RequestBody ProductsDTO productsDTO){
        return productService.insertProducts(productsDTO);
    }

    @GetMapping("getProducts/{sid}")
    public ResponseEntity<?> getProducts(@PathVariable Long sid){
        return productService.getProducts(sid);
    }

    @PostMapping("orderProduct")
    public ResponseEntity<?> orderProduct(@RequestBody OrderProductDTO orderProduct){
        System.out.println(orderProduct + "controller");
        return productService.orderProducts(orderProduct);
    }



}
