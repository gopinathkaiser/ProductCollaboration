package com.ProductsCollaboration.Collaboration.Products.Service.Impl;

import com.ProductsCollaboration.Collaboration.Products.DAO.ProductRepo;
import com.ProductsCollaboration.Collaboration.Products.DTO.ProductsDTO;
import com.ProductsCollaboration.Collaboration.Products.Entity.Products;
import com.ProductsCollaboration.Collaboration.Products.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Override
    public ResponseEntity<?> insertProducts(ProductsDTO productsDTO) {
        try{
            Products products = Products.builder()
                    .UserId(productsDTO.getUserId())
                    .prodName(productsDTO.getProdName())
                    .prodPrice(productsDTO.getProdPrice())
                    .prodDesc(productsDTO.getProdDesc())
                    .build();
            productRepo.save(products);
            return new ResponseEntity<>("Product added",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error Occured",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
