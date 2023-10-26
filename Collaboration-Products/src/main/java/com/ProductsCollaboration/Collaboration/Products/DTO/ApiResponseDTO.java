package com.ProductsCollaboration.Collaboration.Products.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponseDTO {

    HttpStatus httpStatus;

    String message;

    Object data;

}
