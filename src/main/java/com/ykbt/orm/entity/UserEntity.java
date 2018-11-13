package com.ykbt.orm.entity;

import lombok.Data;
import org.seasar.doma.jdbc.entity.NamingType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
@Data
@org.seasar.doma.Entity(naming = NamingType.SNAKE_LOWER_CASE)
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @org.seasar.doma.Column(updatable = false)
    private Integer id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;
}
