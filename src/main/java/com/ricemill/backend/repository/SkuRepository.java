package com.ricemill.backend.repository;

import com.ricemill.backend.entity.Sku;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkuRepository extends JpaRepository<Sku, Long> {

    List<Sku> findByUserId(Long userId);

}