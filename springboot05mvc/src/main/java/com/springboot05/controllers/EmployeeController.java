package com.springboot05.controllers;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot05.dto.EmployeeDTO;

@RestController
public class EmployeeController {
	
	@GetMapping(path ="/")
	public String getMYmsg() {
		return "this is my msg";
	}
	
	@GetMapping("/employees/{employeeId}")
	public EmployeeDTO getEmployeeById(@PathVariable Long employeeId) {
		return new EmployeeDTO(employeeId,"vivek","vivek@gmail.com",27,LocalDate.of(2024, 1, 1),true);
	}
	
	@GetMapping("/employees")
	public String ParmExample(@RequestParam(required = false) Integer age,
			@RequestParam(required = false) String name
			) {
		return "hi your age is "+age+"your name is "+name;
	}

}
 