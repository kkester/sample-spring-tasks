package com.pivotal.tasks.jobs;

import com.pivotal.tasks.users.UserEntity;
import com.pivotal.tasks.users.UserService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

@Configuration
@EnableBatchProcessing
public class JobConfig {

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    @StepScope
    public ItemStreamReader<UserEntity> reader(@Value("#{jobParameters['filePath']}") String filePath) {
        return new FlatFileItemReaderBuilder<UserEntity>()
                .name("reader")
                .resource(resourceLoader.getResource(filePath))
                .delimited()
                .names(new String[] {"name", "email", "city", "state"})
                .fieldSetMapper(new UserFieldSetMapper())
                .build();
    }

    @Bean
    public Job ingestJob() {
        return jobBuilderFactory.get("ingestJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("ingest")
                .<UserEntity, UserEntity>chunk(10)
                .reader(reader(null))
                .writer(writer())
                .build();
    }

    private ItemWriter<UserEntity> writer() {
        return new UserItemWriter(userService);
    }
}
