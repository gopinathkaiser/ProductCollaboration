package com.ProductsCollaboration.Collaboration.Products.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailsDTO {

    private Long prodId;

    private String prodName;

    private Long prodPrice;

    private String prodDesc;
}
