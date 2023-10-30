package com.ProductsCollaboration.Collaboration.Users.Service.Impl;

import com.ProductsCollaboration.Collaboration.Users.DTO.ApiResponseDTO;
import com.ProductsCollaboration.Collaboration.Users.DTO.StatusDTO;
import com.ProductsCollaboration.Collaboration.Users.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseEntity<?> getMessages(String status) {
        try {
            String requestUrl = "http://localhost:9002/message/getMessage/" + status;
            return new ResponseEntity<>(restTemplate.getForObject(requestUrl, ApiResponseDTO.class), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "Error Occurred", e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> updateStatus(StatusDTO status, UUID msgId) {

        try {
            String requestUrl = "http://localhost:9002/message/updateStatus/" + msgId;
            return new ResponseEntity<>(restTemplate.postForObject(requestUrl, status,ApiResponseDTO.class), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "Error Occurred", e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getCollaborations() {
//        try {
//            String requestUrl = "http://localhost:9002/message/updateStatus/" + msgId;
//            return new ResponseEntity<>(restTemplate.postForObject(requestUrl, status,ApiResponseDTO.class), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "Error Occurred", e), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
                    return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "Error Occurred", "done"), HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
