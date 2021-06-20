package com.airton.psjava.repository;

import com.airton.psjava.entities.ShopcartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopcartProductRepository extends JpaRepository<ShopcartProduct, Long> {
}
