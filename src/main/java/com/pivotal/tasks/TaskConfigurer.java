package com.pivotal.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.task.configuration.DefaultTaskConfigurer;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

public class TaskConfigurer extends DefaultTaskConfigurer {

    @Autowired
    private DataSource dataSource;

    public TaskConfigurer(DataSource dataSource) {
        super(dataSource);
    }

}
