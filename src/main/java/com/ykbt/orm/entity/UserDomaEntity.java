package com.ykbt.orm.entity;

import lombok.Data;
import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;


@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "user")
@Data
public class UserDomaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String firstName;


    private String lastName;
}
