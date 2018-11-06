package com.ykbt.orm.service;

import com.ykbt.orm.repository.JpaRepository;
import com.ykbt.orm.resource.UserResource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ykbt.orm.entity.UserEntity;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class JpaServiceImpl implements OrmService {

    @Autowired
    JpaRepository repository;

    public List<UserResource> getUsers() {
        List<UserEntity> entities = repository.findAll();
        List<UserResource> resources = new ArrayList<>();
        entities.stream().forEach(entity -> {
            UserResource resource = new UserResource();
            BeanUtils.copyProperties(entity, resource);
            resources.add(resource);
        });
        return resources;
    }

    public UserResource getUser(Integer id) {
        UserEntity entity = repository.getOne(id);
        UserResource resource = new UserResource();
        BeanUtils.copyProperties(entity, resource);

        return resource;
    }

    @Transactional
    public UserResource addUser(UserResource resource) {
        if (resource.getId() != null) {
            return null;
        }

        return updateUser(resource);
    }

    //saveメソッドは INSERT or UPDATE
    @Transactional
    public UserResource updateUser(UserResource resource) {
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(resource, entity);

        UserEntity addedEntity = repository.saveAndFlush(entity);
        UserResource addedResource = new UserResource();
        BeanUtils.copyProperties(addedEntity, addedResource);

        return addedResource;
    }

    @Transactional
    public void deleteUser(Integer id) {
        repository.deleteById(id);
    }
}
