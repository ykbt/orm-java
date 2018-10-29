package ykbt.orm.service;

import ykbt.orm.resource.UserResource;

import java.util.List;

public interface OrmService {
    public List<UserResource> getUsers();

    public UserResource getUser(Integer id);

    public UserResource addUser(UserResource resource);

    public UserResource updateUser(UserResource resource);

    public void deleteUser(Integer id);
}
