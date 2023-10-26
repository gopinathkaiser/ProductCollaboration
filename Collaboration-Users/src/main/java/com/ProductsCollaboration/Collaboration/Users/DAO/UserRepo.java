package com.ProductsCollaboration.Collaboration.Users.DAO;

import com.ProductsCollaboration.Collaboration.Users.Entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserDetails,Long> {
}
