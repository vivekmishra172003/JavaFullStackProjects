package com.example.addressservice.service;


import com.example.addressservice.entity.Address;
import com.example.addressservice.repository.AddressRepository;
import com.example.addressservice.response.AddressResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    public AddressResponse findAddressByEmployeeId(int employeeId) {
        Optional<Address> addressOptional = addressRepository.findAddressByEmployeeId(employeeId);
        
        if (addressOptional.isPresent()) {
            // Convert Entity to Response object
            return modelMapper.map(addressOptional.get(), AddressResponse.class);
        }
        
        return null;
    }
}
