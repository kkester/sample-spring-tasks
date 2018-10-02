package com.pivotal.tasks.users;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity save(UserEntity user) {
        UserEntity userEntity = userRepository.save(user);
        log.info("Saved User {}", userEntity);
        return userEntity;
    }
}
