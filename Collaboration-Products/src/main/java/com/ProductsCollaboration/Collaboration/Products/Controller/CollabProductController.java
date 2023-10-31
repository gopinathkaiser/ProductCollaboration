package com.ProductsCollaboration.Collaboration.Products.Controller;

import com.ProductsCollaboration.Collaboration.Products.DTO.CollabProductReqDTO;
import com.ProductsCollaboration.Collaboration.Products.DTO.OrderProductDTO;
import com.ProductsCollaboration.Collaboration.Products.Service.CollabProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("collab")
public class CollabProductController {

    @Autowired
    private CollabProductService collabProductService;

    @PostMapping("addCollabProducts")
    public ResponseEntity<?> addCollabProducts(@RequestBody CollabProductReqDTO collabProductReq){
        System.out.println("controller 2");
        System.out.println(collabProductReq);
        return collabProductService.addCollabProducts(collabProductReq);
    }

    @GetMapping("getCollabProducts")
    public ResponseEntity<?> getCollabProducts(){
        return collabProductService.getCollabProduct();
    }

    @PostMapping("orderCollabProduct")
    public ResponseEntity<?> orderCollabProduct(@RequestBody OrderProductDTO orderProduct){
        return collabProductService.orderCollabProducts(orderProduct);
    }


}
