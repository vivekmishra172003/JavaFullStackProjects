package com.example.servicetwo.repository;

import com.example.servicetwo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Custom finder methods
    List<Order> findByCustomerName(String customerName);
    List<Order> findByProductId(Long productId);
}