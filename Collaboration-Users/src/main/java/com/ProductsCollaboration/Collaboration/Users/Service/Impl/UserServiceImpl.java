package com.ProductsCollaboration.Collaboration.Users.Service.Impl;

import com.ProductsCollaboration.Collaboration.Users.DAO.UserRepo;
import com.ProductsCollaboration.Collaboration.Users.DTO.UserDetailsDTO;
import com.ProductsCollaboration.Collaboration.Users.Entity.UserDetails;
import com.ProductsCollaboration.Collaboration.Users.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public ResponseEntity<?> insert(UserDetailsDTO userDetailsDTO) {
        try{
            UserDetails userDetails = UserDetails.builder()
                            .firstName(userDetailsDTO.getFirstName())
                            .lastName(userDetailsDTO.getLastName())
                            .Email(userDetailsDTO.getEmail())
                            .phoneNum(userDetailsDTO.getPhoneNum())
                            .Role(userDetailsDTO.getRole())
                            .Address(userDetailsDTO.getAddress())
                            .build();
            userRepo.save(userDetails);
            return new ResponseEntity<>("Data added",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error Occured" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
