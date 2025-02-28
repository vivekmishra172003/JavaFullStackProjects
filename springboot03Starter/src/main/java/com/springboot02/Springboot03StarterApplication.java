package com.springboot02;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springboot02.bean.Student;

@SpringBootApplication
public class Springboot03StarterApplication implements CommandLineRunner {

	public static void main(String[] args)  {
		SpringApplication.run(Springboot03StarterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		stdBean().displayStdDetails();
		
	}
	@Bean
	public Student stdBean() {
		return new Student("viek",12,28.2f);
	}

}
