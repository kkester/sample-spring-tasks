package com.pivotal.tasks.jobs;

import com.pivotal.tasks.users.UserEntity;
import com.pivotal.tasks.users.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "test")
public class JobConfigTest {

    @Autowired
    private Job job;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testBatchDataProcessing() throws Exception {

        // given
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("filePath", "classpath:user-data.csv")
                .addDate("executionTimeStamp", new Date())
                .toJobParameters();

        // when
        JobExecution jobExecution = jobLauncher.run(job, jobParameters);
        Iterable<UserEntity> userEntities = userRepository.findAll();

        // then
        assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
        assertThat(jobExecution.getStepExecutions()).hasSize(1);
        assertThat(userEntities).hasSize(5);
    }
}