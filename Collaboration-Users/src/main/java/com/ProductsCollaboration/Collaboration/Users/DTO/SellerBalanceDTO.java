package com.ProductsCollaboration.Collaboration.Users.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellerBalanceDTO {

    private Long sellerId;

    private Long remainingPrice;
}
