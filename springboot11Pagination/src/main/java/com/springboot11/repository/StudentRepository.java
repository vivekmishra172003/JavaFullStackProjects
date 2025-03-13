package com.springboot11.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot11.model.Student;

@Repository
public interface StudentRepository extends  JpaRepository<Student, Long> {
	
	Page<Student> findAll(Pageable pageable);
	
	Page<Student> findByCourseContaining(String course,Pageable pageable);
	
	Page<Student> findByFirstNameContainingOrLastNameContaining(String firstName,String lastName,Pageable pageable);
	

}
