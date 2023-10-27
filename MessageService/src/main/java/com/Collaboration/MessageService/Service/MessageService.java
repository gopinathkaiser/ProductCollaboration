package com.Collaboration.MessageService.Service;

import com.Collaboration.MessageService.DTO.ApiResponseDTO;
import com.Collaboration.MessageService.DTO.MessageRequestDTO;
import com.Collaboration.MessageService.Model.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface MessageService {
    ResponseEntity<?> addMessage(MessageRequestDTO messageRequestData);

    ResponseEntity<ApiResponseDTO> getMessages(Status status);

    ResponseEntity<?> updateStatus(Status status, UUID msgId);

    ResponseEntity<?> getMessageBySenderReceiverId(Long sId, Long rId);
}
