 package com.springboot04.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot04.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	

}
