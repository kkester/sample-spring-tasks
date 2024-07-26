package io.spring.tasks.jobs;

import io.spring.tasks.users.UserEntity;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

public class UserFieldSetMapper implements FieldSetMapper<UserEntity> {
    @Override
    public UserEntity mapFieldSet(FieldSet fieldSet) {

        UserEntity user = new UserEntity();
        user.setName(fieldSet.readString(0));
        user.setEmail(fieldSet.readString(1));
        user.setCity(fieldSet.readString(2));
        user.setState(fieldSet.readString(3));
        return user;
    }
}
