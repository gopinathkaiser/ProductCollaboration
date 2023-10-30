package com.ProductsCollaboration.Collaboration.Users.Service;

import com.ProductsCollaboration.Collaboration.Users.DTO.CollabProductReqDTO;
import com.ProductsCollaboration.Collaboration.Users.DTO.OrderProductDTO;
import com.ProductsCollaboration.Collaboration.Users.DTO.UserDetailsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity<?> insert(UserDetailsDTO userDetailsDTO);

    ResponseEntity<?> getProducts(Long uid);

    ResponseEntity<?> addCollabProducts(CollabProductReqDTO productReq);

    ResponseEntity<?> orderProducts(OrderProductDTO orderProduct);

    ResponseEntity<?> orderCollabProducts(OrderProductDTO orderProduct);
}
