package com.ProductsCollaboration.Collaboration.Users.Service.Impl;

import com.ProductsCollaboration.Collaboration.Users.DAO.SellerBalanceRepo;
import com.ProductsCollaboration.Collaboration.Users.DAO.UserRepo;
import com.ProductsCollaboration.Collaboration.Users.DTO.ApiResponseDTO;
import com.ProductsCollaboration.Collaboration.Users.DTO.SellerBalanceDTO;
import com.ProductsCollaboration.Collaboration.Users.DTO.StatusDTO;
import com.ProductsCollaboration.Collaboration.Users.Entity.SellerBalance;
import com.ProductsCollaboration.Collaboration.Users.Service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SellerBalanceRepo sellerBalanceRepo;
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

    @Override
    public ResponseEntity<?> addSellerBalance(List<SellerBalanceDTO> sellerBalanceDTO) {
        try{
            System.out.println(sellerBalanceDTO);
            for(SellerBalanceDTO sellerBalance : sellerBalanceDTO) {
                Optional<SellerBalance> sellerHistory = sellerBalanceRepo.findById(sellerBalance.getSellerId());
                if (sellerHistory.isEmpty()) {
                    SellerBalance sellerBalanceClass = modelMapper.map(sellerBalance, SellerBalance.class);
                    System.out.println(sellerBalanceClass + "is empty");
                    sellerBalanceRepo.save(sellerBalanceClass);
                    return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.OK,"success",sellerBalanceClass), HttpStatus.OK);

                }

                SellerBalance sellerBalanceClass = SellerBalance.builder()
                        .sellerId(sellerBalance.getSellerId())
                        .remainingPrice(sellerHistory.get().getRemainingPrice() + sellerBalance.getRemainingPrice())
                        .build();
                System.out.println(sellerBalanceClass);
                sellerBalanceRepo.save(sellerBalanceClass);
            }
            return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.OK,"success",sellerBalanceDTO), HttpStatus.OK);

        }catch (Exception e) {
            return new ResponseEntity<>(new ApiResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "failed" + e, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
