package com.ProductsCollaboration.Collaboration.Products.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CollabProductReqDTO {

    private List<ProductsIDDTO> productsList;

}
