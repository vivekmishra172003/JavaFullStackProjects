package com.springboot04.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot04.entities.Student;
import com.springboot04.repository.StudentRepository;
@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public boolean addStudentDetails(Student std) {
		// TODO Auto-generated method stub
		boolean status = false;
		try {
			studentRepository.save(std);
			status = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}

}
 