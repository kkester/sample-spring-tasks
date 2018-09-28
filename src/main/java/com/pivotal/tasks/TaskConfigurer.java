package com.pivotal.tasks;

import org.springframework.cloud.task.configuration.DefaultTaskConfigurer;

import javax.sql.DataSource;

public class TaskConfigurer extends DefaultTaskConfigurer {

    private DataSource dataSource;

    public TaskConfigurer(DataSource dataSource) {
        super(dataSource);
    }

}
