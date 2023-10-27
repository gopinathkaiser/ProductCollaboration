package com.Collaboration.MessageService.Service.Impl;

import com.Collaboration.MessageService.DAO.MessageServiceRepo;
import com.Collaboration.MessageService.DTO.ApiResponseDTO;
import com.Collaboration.MessageService.DTO.MessageRequestDTO;
import com.Collaboration.MessageService.Entity.MessageDetails;
import com.Collaboration.MessageService.Model.Status;
import com.Collaboration.MessageService.Service.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.Collaboration.MessageService.Model.Status.ALL;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageServiceRepo messageServiceRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<?> addMessage(MessageRequestDTO messageRequestData) {
        try{
            MessageDetails messageDetails = modelMapper.map(messageRequestData,MessageDetails.class);
            messageDetails.setAdminStatus(Status.PENDING);
            System.out.println(messageDetails);
            messageServiceRepo.save(messageDetails);
            return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.OK,"Data Added Successfully",messageDetails),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR,"Error Occurred" ,e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ApiResponseDTO> getMessages(Status status) {
        try{
            List<MessageDetails> messageDetails;
            if(status.equals(ALL))
                messageDetails = messageServiceRepo.findAll();
            else
                messageDetails = messageServiceRepo.findAllByAdminStatus(status);
            return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.OK,messageDetails.isEmpty() ? "No data found" : "Success",messageDetails),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR,"Error Occurred" ,e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> updateStatus(Status status, UUID msgId) {
        try{
            System.out.println(status + " " + msgId);
            Optional<MessageDetails> messageDetails = messageServiceRepo.findById(msgId);
            if(messageDetails.isEmpty())
                return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.OK, "No data found for msg id",messageDetails),HttpStatus.OK);

            MessageDetails messageData = messageDetails.get();
            messageData.setAdminStatus(status);
            messageServiceRepo.save(messageData);
            return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.OK,"Successfully modified",messageData),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR,"Error Occurred" ,e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
