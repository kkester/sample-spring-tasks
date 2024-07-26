package io.spring.tasks.users;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserEntity save(UserEntity user) {
        UserEntity userEntity = userRepository.save(user);
        log.info("Saved User {}", userEntity);
        return userEntity;
    }
}
