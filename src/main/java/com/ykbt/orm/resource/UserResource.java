package com.ykbt.orm.resource;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class UserResource {
    private Integer id;

    @Size(min = 1, max = 20)
    private String firstName;

    @Size(min = 1, max = 20)
    private String lastName;
}
