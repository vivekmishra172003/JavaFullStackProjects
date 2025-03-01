package com.springboot04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.springboot04.entities.Student;
import com.springboot04.services.StudentService;
import com.springboot04.services.StudentServiceImpl;

@SpringBootApplication
public class Springboot04withDataJpaApplication {

	public static void main(String[] args) {
		ApplicationContext context =  SpringApplication.run(Springboot04withDataJpaApplication.class, args);
		StudentService stdService =   context.getBean(StudentServiceImpl.class);
		Student std = new Student();
		std.setName("vivek");
		std.setRollno(101);
		std.setMarks(93.5f);
		boolean status = stdService.addStudentDetails(std); 
		System.out.println(status);
	}

}
