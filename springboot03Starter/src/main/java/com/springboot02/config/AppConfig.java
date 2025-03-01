package com.springboot02.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springboot02.bean.Student;

@Configuration
public class AppConfig {
	@Bean
	public CommandLineRunner cmdLineRunner() {
		return  new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				// TODO Auto-generated method stub
				stdBean().displayStdDetails();
			} 
		};
	}
	@Bean
	public Student stdBean() {
		return new Student("viek",12,28.2f);
	}

}
