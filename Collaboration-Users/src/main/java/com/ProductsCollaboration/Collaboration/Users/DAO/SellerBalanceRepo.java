package com.ProductsCollaboration.Collaboration.Users.DAO;

import com.ProductsCollaboration.Collaboration.Users.Entity.SellerBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerBalanceRepo extends JpaRepository<SellerBalance,Long> {
}
