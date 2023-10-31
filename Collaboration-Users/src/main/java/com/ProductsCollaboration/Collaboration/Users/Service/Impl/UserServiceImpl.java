package com.ProductsCollaboration.Collaboration.Users.Service.Impl;

import com.ProductsCollaboration.Collaboration.Users.DAO.SellerBalanceRepo;
import com.ProductsCollaboration.Collaboration.Users.DAO.UserRepo;
import com.ProductsCollaboration.Collaboration.Users.DTO.*;
import com.ProductsCollaboration.Collaboration.Users.Entity.SellerBalance;
import com.ProductsCollaboration.Collaboration.Users.Entity.UserDetails;
import com.ProductsCollaboration.Collaboration.Users.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SellerBalanceRepo sellerBalanceRepo;

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

    @Override
    public ResponseEntity<?> addCollabProducts(CollabProductReqDTO productReq) {
        try{
            System.out.println("service 1.1");
            String productsUrl = "http://localhost:9001/collab/addCollabProducts" ;
            System.out.println(productReq);

            ApiResponseDTO responseEntity = restTemplate.postForObject(productsUrl,productReq,ApiResponseDTO.class);
            System.out.println("service 1.2");
            return new ResponseEntity<>(responseEntity,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Error Occurred" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> orderProducts(OrderProductDTO orderProduct) {
        try {
            String orderProductUrl = "http://localhost:9001/orderProduct";
            ApiResponseDTO responseEntity = restTemplate.postForObject(orderProductUrl,orderProduct,ApiResponseDTO.class);
            return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.OK,"success",responseEntity), HttpStatus.OK);

        }catch (Exception e) {
            return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "failed" + e, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> orderCollabProducts(OrderProductDTO orderProduct) {
        try{
            String orderProductUrl = "http://localhost:9001/collab/orderCollabProduct";
            ApiResponseDTO responseEntity = restTemplate.postForObject(orderProductUrl,orderProduct,ApiResponseDTO.class);
            return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.OK,"success",responseEntity), HttpStatus.OK);

        }catch (Exception e) {
            return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "failed" + e, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
