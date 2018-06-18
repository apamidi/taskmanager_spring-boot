package com.aravinda.taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@Configuration
@EnableAutoConfiguration 
@EnableWebMvc
@ComponentScan(basePackages = {"com.aravinda.taskmanager.controller",
        "com.aravinda.taskmanager.dao",
        "com.aravinda.taskmanager.dto","com.aravinda.taskmanager.service"})
/* Task Manager application starting class*/
public class TaskmanagerApplication{
	
		
	public static void main(String[] args) {
		SpringApplication.run(TaskmanagerApplication.class, args);
	}
	
	
}
