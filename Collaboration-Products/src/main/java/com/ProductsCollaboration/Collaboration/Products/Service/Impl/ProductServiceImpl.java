package com.ProductsCollaboration.Collaboration.Products.Service.Impl;

import com.ProductsCollaboration.Collaboration.Products.DAO.CollabProductRepo;
import com.ProductsCollaboration.Collaboration.Products.DAO.ProductRepo;
import com.ProductsCollaboration.Collaboration.Products.DTO.*;
import com.ProductsCollaboration.Collaboration.Products.Entity.CollabProducts;
import com.ProductsCollaboration.Collaboration.Products.Entity.Products;
import com.ProductsCollaboration.Collaboration.Products.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CollabProductRepo collabProductRepo;

    @Override
    public ResponseEntity<?> insertProducts(ProductsDTO productsDTO) {
        try {
            Products products = Products.builder()
                    .userId(productsDTO.getUserId())
                    .prodName(productsDTO.getProdName())
                    .prodPrice(productsDTO.getProdPrice())
                    .prodDesc(productsDTO.getProdDesc())
                    .quantity(productsDTO.getQuantity())
                    .build();
            productRepo.save(products);
            return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.OK, "Data Added Successfully", products), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "Error Occured", e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ApiResponseDTO> getProducts(Long sid) {
        try {

            List<Products> products = productRepo.findAllByUserId(sid);
            List<ProductDetailsDTO> productDetailsList = new ArrayList<>();

            for (Products productsClass : products) {
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

            return new ResponseEntity<>(apiResponseDTO, apiResponseDTO.getHttpStatus());
        } catch (Exception e) {

            return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "failed", null), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @Override
    public ResponseEntity<?> orderProducts(OrderProductDTO orderProduct) {
        try {
            System.out.println("service 1.1");
            Optional<Products> productsFromDb = productRepo.findById(orderProduct.getpId());
            System.out.println("data from db" + productsFromDb);
            if(productsFromDb.isEmpty()){
                return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.NOT_FOUND, "Product not found", null), HttpStatus.NOT_FOUND);
            }
            Products products = productsFromDb.get();
            if(orderProduct.getQuantity() > products.getQuantity()){
                return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.NOT_FOUND, "Only " + productsFromDb.get().getQuantity() + " available", null), HttpStatus.NOT_FOUND);
            }
            Integer remainingQuantity = productsFromDb.get().getQuantity() - orderProduct.getQuantity();
            List<CollabProducts> collabProducts = collabProductRepo.findCollabProducts(orderProduct.getpId());
            System.out.println(collabProducts.isEmpty());
            if(!collabProducts.isEmpty()){
                for(CollabProducts collabProductsClass : collabProducts){
                    Integer minQuantity = remainingQuantity;
                    minQuantity = Math.min(collabProductsClass.getQuantity(),minQuantity);
                    collabProductsClass.setQuantity(minQuantity);
                    collabProductRepo.save(collabProductsClass);
                }
            }
            products.setQuantity(remainingQuantity);
            productRepo.save(products);
            System.out.println("products " + products);
            return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.OK, "success", products), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "failed " + e, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
