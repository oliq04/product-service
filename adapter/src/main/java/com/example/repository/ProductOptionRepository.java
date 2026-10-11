package com.example.repository;

import com.example.entity.ProductOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOptionEntity, Long> {

    List<ProductOptionEntity> findAllByOptionProduct_Id(Long productId);

}
