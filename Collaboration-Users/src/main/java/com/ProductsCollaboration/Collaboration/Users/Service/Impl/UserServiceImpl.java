package com.ProductsCollaboration.Collaboration.Users.Service.Impl;

import com.ProductsCollaboration.Collaboration.Users.DAO.UserRepo;
import com.ProductsCollaboration.Collaboration.Users.DTO.ApiResponseDTO;
import com.ProductsCollaboration.Collaboration.Users.DTO.UserDetailsDTO;
import com.ProductsCollaboration.Collaboration.Users.Entity.UserDetails;
import com.ProductsCollaboration.Collaboration.Users.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestTemplate restTemplate;

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
            return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.OK,"Data Added Successfully",userDetails),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR,"Error Occurred" ,e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getProducts(Long uid) {

        try{
            String productsUrl = "http://localhost:9001/getProducts/" + uid;

            ResponseEntity<ApiResponseDTO> responseEntity = restTemplate.getForEntity(productsUrl,ApiResponseDTO.class);
            return new ResponseEntity<>(responseEntity.getBody(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error Occurred" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
