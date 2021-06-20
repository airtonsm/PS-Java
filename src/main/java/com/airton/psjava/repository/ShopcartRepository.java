package com.airton.psjava.repository;

import com.airton.psjava.entities.Shopcart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopcartRepository extends JpaRepository<Shopcart, Long> {
}
