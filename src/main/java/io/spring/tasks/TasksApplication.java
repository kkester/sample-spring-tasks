package io.spring.tasks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
@EnableTask
@EnableBatchProcessing
@Slf4j
public class TasksApplication {
	public static void main(String[] args) {
		SpringApplication.run(TasksApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(JobLauncher jobLauncher, Job job) {
		return args -> {
			log.info("Hello World", args.getSourceArgs());
			JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
			Arrays.stream(args.getSourceArgs())
				.filter(arg -> arg.contains("="))
				.map(arg -> arg.split("="))
				.filter(arg -> arg.length == 2)
				.forEach(arg -> jobParametersBuilder.addString(arg[0], arg[1]));
			jobLauncher.run(job, jobParametersBuilder.toJobParameters());
		};
	}
}
