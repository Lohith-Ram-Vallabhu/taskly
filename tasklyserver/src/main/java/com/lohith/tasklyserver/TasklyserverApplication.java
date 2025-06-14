package com.lohith.tasklyserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.lohith.tasklyserver")
public class TasklyserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasklyserverApplication.class, args);
	}

}
