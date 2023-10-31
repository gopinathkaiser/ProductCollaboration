package com.ProductsCollaboration.Collaboration.Users.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellerBalance {

    @Id
    private Long sellerId;

    private Long remainingPrice;
}
