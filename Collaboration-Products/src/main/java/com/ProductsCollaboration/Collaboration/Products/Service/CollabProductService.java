package com.ProductsCollaboration.Collaboration.Products.Service;

import com.ProductsCollaboration.Collaboration.Products.DTO.CollabProductReqDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CollabProductService {

    ResponseEntity<?> addCollabProducts(CollabProductReqDTO collabProductReq);

    ResponseEntity<?> getCollabProduct();
}
