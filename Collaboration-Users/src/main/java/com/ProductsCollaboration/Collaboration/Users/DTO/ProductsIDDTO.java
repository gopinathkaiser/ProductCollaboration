package com.ProductsCollaboration.Collaboration.Users.DTO;

import lombok.*;

//@Setter
//@ToString
//@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductsIDDTO {

    private Integer pId;

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    @Override
    public String toString() {
        return "ProductsIDDTO{" +
                "pId=" + pId +
                '}';
    }
}

