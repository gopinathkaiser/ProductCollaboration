package com.ProductsCollaboration.Collaboration.Users.Service;

import com.ProductsCollaboration.Collaboration.Users.DTO.UserDetailsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity<?> insert(UserDetailsDTO userDetailsDTO);

    ResponseEntity<?> getProducts(Long uid);


}
