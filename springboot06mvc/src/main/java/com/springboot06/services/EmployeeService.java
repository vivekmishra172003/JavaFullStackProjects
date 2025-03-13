package com.springboot06.services;

import org.springframework.stereotype.Service;

import com.springboot06.DTO.EmployeeDTO;
import com.springboot06.entites.EmployeeEntity;
import com.springboot06.repos.EmployeeRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	public EmployeeService(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}
	public  EmployeeEntity getEmployeeId(Long id) {
		// TODO Auto-generated method stub
		
		return employeeRepository.findById(id).orElse(null);
	}

}
 