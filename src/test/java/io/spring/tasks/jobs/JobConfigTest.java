package io.spring.tasks.jobs;

import io.spring.tasks.users.UserEntity;
import io.spring.tasks.users.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles(profiles = "test")
class JobConfigTest {

    @Autowired
    Job job;

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    UserRepository userRepository;

    @Test
    void testBatchDataProcessing() throws Exception {
        // arrange
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("filePath", "classpath:user-data.csv")
                .addDate("executionTimeStamp", new Date())
                .toJobParameters();

        // act
        JobExecution jobExecution = jobLauncher.run(job, jobParameters);
        Iterable<UserEntity> userEntities = userRepository.findAll();

        // assert
        assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
        assertThat(jobExecution.getStepExecutions()).hasSize(1);
        assertThat(userEntities).hasSize(5);
    }
}