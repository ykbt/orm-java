package com.ykbt.orm.service;

import com.ykbt.orm.resource.UserResource;

import java.util.List;

public interface OrmService {
    List<UserResource> getUsers();

    UserResource getUser(Integer id);

    UserResource addUser(UserResource resource);

    UserResource updateUser(UserResource resource);

    void deleteUser(Integer id);
}
