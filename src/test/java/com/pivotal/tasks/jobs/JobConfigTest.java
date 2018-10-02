package com.pivotal.tasks.jobs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobConfigTest {

    @Autowired
    private Job job;

    @Autowired
    private JobLauncher jobLauncher;

    @Test
    public void testBatchDataProcessing() throws Exception {

        // given
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("filePath", "classpath:data.csv")
                .addDate("executionTimeStamp", new Date())
                .toJobParameters();

        // when
        JobExecution jobExecution = jobLauncher.run(job, jobParameters);

        // then
        assertEquals("Incorrect batch status", BatchStatus.COMPLETED, jobExecution.getStatus());
        assertEquals("Invalid number of step executions", 1, jobExecution.getStepExecutions().size());
    }
}