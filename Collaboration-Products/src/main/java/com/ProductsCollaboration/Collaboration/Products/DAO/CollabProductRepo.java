package com.ProductsCollaboration.Collaboration.Products.DAO;

import com.ProductsCollaboration.Collaboration.Products.Entity.CollabProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollabProductRepo extends JpaRepository<CollabProducts,Long> {

//    @Query("select cp from CollabProducts cp join Products p where p.pId = :pId")
//    List<CollabProducts> findCollabProducts(Long pId);


    @Query("SELECT cp FROM CollabProducts cp JOIN cp.productsList p WHERE p.pId = :pId")
    List<CollabProducts> findCollabProducts(@Param("pId") Long pId);

}
