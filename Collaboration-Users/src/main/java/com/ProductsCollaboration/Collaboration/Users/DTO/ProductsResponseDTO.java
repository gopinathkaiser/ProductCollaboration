package com.ProductsCollaboration.Collaboration.Users.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductsResponseDTO {

    private Long userId;

    private List<ProductDTO> products;
}


