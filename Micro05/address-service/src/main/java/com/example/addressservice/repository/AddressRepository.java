package com.example.addressservice.repository;


import com.example.addressservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    
    // Custom query to find address by employee ID
    @Query(nativeQuery = true, 
           value = "SELECT * FROM address WHERE employee_id = :employeeId")
    Optional<Address> findAddressByEmployeeId(@Param("employeeId") int employeeId);
}