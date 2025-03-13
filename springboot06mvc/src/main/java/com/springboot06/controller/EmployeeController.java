package com.springboot06.controller;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot06.DTO.EmployeeDTO;
import com.springboot06.entites.EmployeeEntity;
import com.springboot06.services.EmployeeService;

@RestController
public class EmployeeController {
	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	@GetMapping(path ="/")
	public String getMYmsg() {
		return "this is my msg";
	}
	
	@GetMapping("/employees/{employeeId}")
	public EmployeeEntity getEmployeeById(@PathVariable(name="employeeId") Long id) {
		return employeeService.getEmployeeId(id) ;
	}

	
	@GetMapping("/employees")
	public String ParmExample(@RequestParam(required = false) Integer age,
			@RequestParam(required = false) String name
			) {
		return "hi your age is "+age+"your name is "+name;
	}
	


}
 