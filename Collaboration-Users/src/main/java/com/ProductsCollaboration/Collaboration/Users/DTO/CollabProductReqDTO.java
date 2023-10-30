package com.ProductsCollaboration.Collaboration.Users.DTO;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CollabProductReqDTO {

    private List<ProductsIDDTO> productsList;

//    public List<ProductsIDDTO> getProductsList() {
//        return productsList;
//    }
//
//    public void setProductsList(List<ProductsIDDTO> productsList) {
//        this.productsList = productsList;
//    }
}
