package com.ProductsCollaboration.Collaboration.Users.Controller;

import com.ProductsCollaboration.Collaboration.Users.DTO.UserDetailsDTO;
import com.ProductsCollaboration.Collaboration.Users.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("addUser")
    public ResponseEntity<?> insertUser(@RequestBody UserDetailsDTO userDetailsDTO){
        return userService.insert(userDetailsDTO);
    }

}
