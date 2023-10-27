package com.ProductsCollaboration.Collaboration.Users.Service;

import com.ProductsCollaboration.Collaboration.Users.DTO.StatusDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface AdminService {
    ResponseEntity<?> getMessages(String status);

    ResponseEntity<?> updateStatus(StatusDTO status, UUID msgId);
}
