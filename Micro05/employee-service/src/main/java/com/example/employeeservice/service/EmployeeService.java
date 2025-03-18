package com.example.employeeservice.service;

import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.feignclient.AddressClient;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.response.AddressResponse;
import com.example.employeeservice.response.EmployeeResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AddressClient addressClient;

    public EmployeeResponse getEmployeeById(int id) {
        // Find employee by id
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        
        if (employeeOptional.isPresent()) {
            // Convert Employee entity to EmployeeResponse
            EmployeeResponse employeeResponse = modelMapper.map(employeeOptional.get(), EmployeeResponse.class);
            
            // Call Address Service using FeignClient
            ResponseEntity<AddressResponse> addressResponse = addressClient.getAddressByEmployeeId(id);
            
            // Set address in employee response
            if (addressResponse.getStatusCode().is2xxSuccessful()) {
                employeeResponse.setAddressResponse(addressResponse.getBody());
            }
            
            return employeeResponse;
        }
        
        return null;
    }
}