package io.spring.tasks.jobs;

import io.spring.tasks.users.UserEntity;
import io.spring.tasks.users.UserService;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class UserItemWriter implements ItemWriter<UserEntity> {

    private UserService userService;

    public UserItemWriter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void write(Chunk<? extends UserEntity> items) throws Exception {
        if (items != null) {
            items.forEach(u -> userService.save(u));
        }
    }
}
