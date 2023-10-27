package com.ProductsCollaboration.Collaboration.Users.Controller;

import com.ProductsCollaboration.Collaboration.Users.DTO.StatusDTO;
import com.ProductsCollaboration.Collaboration.Users.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("Admin")
public class AdminController {

    @Autowired
    private  AdminService adminService;

    @PostMapping("getMessage")
    public ResponseEntity<?> getMessage(@RequestBody StatusDTO status){
        return adminService.getMessages(status.getStatus());
    }

    @PostMapping("updateStatus/{msgId}")
    public ResponseEntity<?> updateStatus(@RequestBody StatusDTO statusDTO, @PathVariable UUID msgId){
        return adminService.updateStatus(statusDTO,msgId);
    }

}
