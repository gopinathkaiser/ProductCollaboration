package com.ProductsCollaboration.Collaboration.Users.Controller;

import com.ProductsCollaboration.Collaboration.Users.DTO.CollabProductReqDTO;
import com.ProductsCollaboration.Collaboration.Users.DTO.UserDetailsDTO;
import com.ProductsCollaboration.Collaboration.Users.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("addUser")
    public ResponseEntity<?> insertUser(@RequestBody UserDetailsDTO userDetailsDTO){
        return userService.insert(userDetailsDTO);
    }

    @GetMapping("getProducts/{uid}")
    public ResponseEntity<?> getProducts(@PathVariable Long uid){
        return userService.getProducts(uid);
    }

    @PostMapping("addCollabProducts")
    public ResponseEntity<?> addCollabProducts(@RequestBody CollabProductReqDTO productReq){
        System.out.println(productReq);
        System.out.println("controller 1");
        return userService.addCollabProducts(productReq);
    }

}
