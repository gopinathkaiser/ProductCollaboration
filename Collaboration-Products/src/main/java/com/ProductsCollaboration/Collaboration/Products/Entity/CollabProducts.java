package com.ProductsCollaboration.Collaboration.Products.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CollabProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long collabProductId;

    @ManyToMany
    private List<Products> productsList;

    private Integer quantity;

    private Long price;
}
