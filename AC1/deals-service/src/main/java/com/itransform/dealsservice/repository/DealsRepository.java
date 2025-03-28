package com.itransform.dealsservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itransform.dealsservice.model.Deal;

public interface DealsRepository extends JpaRepository<Deal, Long> {
    List<Deal> findByCategory(String category);
    List<Deal> findByIsActiveTrue();
    List<Deal> findByCategoryAndIsActiveTrue(String category);
}