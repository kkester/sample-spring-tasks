package io.spring.tasks.jobs;

import io.spring.tasks.users.UserEntity;
import io.spring.tasks.users.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
@Slf4j
public class JobConfig {

    private final ResourceLoader resourceLoader;

    @Bean
    @JobScope
    public ItemStreamReader<UserEntity> userEntityItemStreamReader(@Value("#{jobParameters['filePath']}") String filePath) {
        log.info("***************** reading {} *****************", filePath);
        return new FlatFileItemReaderBuilder<UserEntity>()
            .name("reader")
            .resource(resourceLoader.getResource(filePath))
            .delimited()
            .names("name", "email", "city", "state")
            .fieldSetMapper(new UserFieldSetMapper())
            .build();
    }

    @Bean
    public ItemWriter<UserEntity> writer(UserService userService) {
        return new UserItemWriter(userService);
    }

    @Bean
    public Step ingestStep(JobRepository jobRepository,
                           @Qualifier("springCloudTaskTransactionManager") PlatformTransactionManager transactionManager,
                           ItemStreamReader<UserEntity> userEntityItemStreamReader,
                           ItemWriter<UserEntity> itemWriter) {
        return new StepBuilder("ingest", jobRepository)
            .<UserEntity, UserEntity>chunk(10, transactionManager)
            .reader(userEntityItemStreamReader)
            .writer(itemWriter)
            .build();
    }

    @Bean
    public Job ingestJob(JobRepository jobRepository, Step ingestStep) {
        return new JobBuilder("ingestJob", jobRepository)
            .incrementer(new RunIdIncrementer())
            .flow(ingestStep)
            .end()
            .build();
    }
}
