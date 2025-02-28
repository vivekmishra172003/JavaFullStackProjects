package com.springboot01;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Springboot01StandAloneApplication implements CommandLineRunner {

	public static void main(String[] args) {
		System.out.println("Application started...");
		SpringApplication.run(Springboot01StandAloneApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		myClass().printMessage("vivek");
		
	}
	@Bean
	public MyClass myClass() {
		return new MyClass();
	}
 
}
 