package com.pivotal.tasks.users;

import lombok.Data;

import javax.persistence.*;

@NamedNativeQuery(name = "User.findUsersByState",
        query = "select u.* \n" +
                "from user u \n" +
                "where lower(state) = :state",
        resultClass = UserEntity.class
)

@Entity
@Table(name="USER")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String email;

    private String city;

    private String state;

}
