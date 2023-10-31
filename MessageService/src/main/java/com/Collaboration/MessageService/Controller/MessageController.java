package com.Collaboration.MessageService.Controller;

import com.Collaboration.MessageService.DTO.ApiResponseDTO;
import com.Collaboration.MessageService.DTO.MessageRequestDTO;
import com.Collaboration.MessageService.DTO.StatusDTO;
import com.Collaboration.MessageService.Model.Status;
import com.Collaboration.MessageService.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping()
    public ResponseEntity<?> addMessage(@RequestBody MessageRequestDTO messageRequestData){
       return messageService.addMessage(messageRequestData);
    }

    @GetMapping("getMessage/{status}")
    public ResponseEntity<ApiResponseDTO> getMessage(@PathVariable Status status){
        return messageService.getMessages(status);
    }

    @PostMapping("updateStatus/{msgId}")
    public ResponseEntity<?> updateStatus(@RequestBody StatusDTO status, @PathVariable UUID msgId){
        return messageService.updateStatus(status.getStatus(),msgId);
    }

    @GetMapping("getMessage/{sId}/{rId}")
    public ResponseEntity<?> getMessage(@PathVariable Long sId,@PathVariable Long rId){
        return messageService.getMessageBySenderReceiverId(sId,rId);
    }
}
