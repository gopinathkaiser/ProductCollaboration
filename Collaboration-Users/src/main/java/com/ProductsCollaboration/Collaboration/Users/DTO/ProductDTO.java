package com.ProductsCollaboration.Collaboration.Users.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long prodId;

    private String prodName;

    private Long prodPrice;

    private String prodDesc;
}


