package com.ProductsCollaboration.Collaboration.Products.Service.Impl;

import com.ProductsCollaboration.Collaboration.Products.DAO.ProductRepo;
import com.ProductsCollaboration.Collaboration.Products.DTO.ApiResponseDTO;
import com.ProductsCollaboration.Collaboration.Products.DTO.ProductDetailsDTO;
import com.ProductsCollaboration.Collaboration.Products.DTO.ProductsDTO;
import com.ProductsCollaboration.Collaboration.Products.DTO.ProductsResponseDTO;
import com.ProductsCollaboration.Collaboration.Products.Entity.Products;
import com.ProductsCollaboration.Collaboration.Products.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Override
    public ResponseEntity<?> insertProducts(ProductsDTO productsDTO) {
        try{
            Products products = Products.builder()
                    .userId(productsDTO.getUserId())
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

    @Override
    public ResponseEntity<ApiResponseDTO> getProducts(Long sid) {
        try{

            List<Products> products = productRepo.findAllByUserId(sid);
            List<ProductDetailsDTO> productDetailsList = new ArrayList<>();

            for(Products productsClass : products){
                ProductDetailsDTO productsDetails = ProductDetailsDTO.builder()
                        .prodId(productsClass.getPId())
                        .prodName(productsClass.getProdName())
                        .prodPrice(productsClass.getProdPrice())
                        .prodDesc(productsClass.getProdDesc())
                        .build();
                productDetailsList.add(productsDetails);
            }

            ProductsResponseDTO productsResponse = ProductsResponseDTO.builder()
                    .UserId(sid)
                    .products(productDetailsList)
                    .build();
            System.out.println(productsResponse);
            ApiResponseDTO apiResponseDTO = ApiResponseDTO.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("success")
                    .data(productsResponse)
                    .build();

            return new ResponseEntity<>(apiResponseDTO,apiResponseDTO.getHttpStatus());
        }catch (Exception e){

            return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR,"failed",null),HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
