package com.ProductsCollaboration.Collaboration.Products.Service.Impl;

import com.ProductsCollaboration.Collaboration.Products.DAO.CollabProductRepo;
import com.ProductsCollaboration.Collaboration.Products.DAO.ProductRepo;
import com.ProductsCollaboration.Collaboration.Products.DTO.ApiResponseDTO;
import com.ProductsCollaboration.Collaboration.Products.DTO.CollabProductReqDTO;
import com.ProductsCollaboration.Collaboration.Products.DTO.OrderProductDTO;
import com.ProductsCollaboration.Collaboration.Products.Entity.CollabProducts;
import com.ProductsCollaboration.Collaboration.Products.Entity.Products;
import com.ProductsCollaboration.Collaboration.Products.Service.CollabProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CollabProductImpl implements CollabProductService {

    @Autowired
    private CollabProductRepo collabProductRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductRepo productRepo;

    @Override
    public ResponseEntity<?> addCollabProducts(CollabProductReqDTO collabProductReq) {
        try{
            System.out.println(collabProductReq);
            CollabProducts collabProducts = modelMapper.map(collabProductReq,CollabProducts.class);
            System.out.println(collabProducts);
            List<Products> products = collabProducts.getProductsList();
            Integer minQuantity = Integer.MAX_VALUE;
            Long price = 0l;
            for(Products productsData : products){
                Optional<Products> singleProductsOptional = productRepo.findById(productsData.getPId());
                if(singleProductsOptional.isEmpty()){
                    System.out.println(products);
                    return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.NOT_FOUND,"product not found",productsData.getPId()), HttpStatus.NOT_FOUND);
                }
                Products singleProducts = singleProductsOptional.get();
                minQuantity = Math.min(singleProducts.getQuantity(),minQuantity);
                price += singleProducts.getProdPrice();
            }
            System.out.println(minQuantity);
            collabProducts.setQuantity(minQuantity);
            collabProducts.setPrice(price);
            collabProductRepo.save(collabProducts);
            System.out.println("service 2.2");
            return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.OK,"success",collabProductReq), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "failed", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getCollabProduct() {
        try{

            List<CollabProducts> collabProductsList = collabProductRepo.findAll();
            ApiResponseDTO apiResponse = ApiResponseDTO.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("success")
                    .data(collabProductsList)
                    .build();

            return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
        }catch (Exception e) {
            return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "failed", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> orderCollabProducts(OrderProductDTO orderProduct) {
        try{
            Optional<CollabProducts> collabProduct = collabProductRepo.findById(orderProduct.getpId());
            if(collabProduct.isEmpty())
                return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.NOT_FOUND,"product not found",orderProduct.getpId()), HttpStatus.NOT_FOUND);

            CollabProducts collabProductsClass = collabProduct.get();

            if(collabProductsClass.getQuantity() == 0 )
                return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.OK, "Sold out", null), HttpStatus.OK);

            if(collabProductsClass.getQuantity() < orderProduct.getQuantity())
                return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.OK, "Only " + collabProductsClass.getQuantity() + " available", null), HttpStatus.OK);

            int finalQuantity = collabProductsClass.getQuantity() - orderProduct.getQuantity();
            collabProductsClass.setQuantity(finalQuantity);

            List<Products> productsInCollab = collabProductsClass.getProductsList();
            for(Products products : productsInCollab){
                products.setQuantity(products.getQuantity() - orderProduct.getQuantity());
                productRepo.save(products);
            }
            collabProductRepo.save(collabProductsClass);

            return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.OK,"success",collabProductsClass), HttpStatus.OK);

        }catch (Exception e) {
            return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "failed", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
