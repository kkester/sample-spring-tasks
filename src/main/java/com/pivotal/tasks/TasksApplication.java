package com.pivotal.tasks;

import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;

@SpringBootApplication
@EnableTask
public class TasksApplication {

	Logger logger = new Logger()

	public static void main(String[] args) {
		SpringApplication.run(TasksApplication.class, args);
	}

}
