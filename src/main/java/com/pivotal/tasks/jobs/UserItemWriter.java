package com.pivotal.tasks.jobs;

import com.pivotal.tasks.users.UserEntity;
import com.pivotal.tasks.users.UserService;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class UserItemWriter implements ItemWriter<UserEntity> {

    private UserService userService;

    public UserItemWriter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void write(List<? extends UserEntity> items) {
        items.forEach(u -> userService.save(u));
    }
}
