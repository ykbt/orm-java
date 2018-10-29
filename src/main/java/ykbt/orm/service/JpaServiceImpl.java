package ykbt.orm.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ykbt.orm.entity.UserEntity;
import ykbt.orm.repository.JpaRepository;
import ykbt.orm.resource.UserResource;

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

    public UserResource addUser(UserResource resource) {
        if (resource.getId() != null) {
            return null;
        }

        return updateUser(resource);
    }

    //saveメソッドは INSERT or UPDATE
    public UserResource updateUser(UserResource resource) {
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(resource, entity);

        UserEntity addedEntity = repository.saveAndFlush(entity);
        UserResource addedResource = new UserResource();
        BeanUtils.copyProperties(addedEntity, addedResource);

        return addedResource;
    }

    public void deleteUser(Integer id) {
        repository.deleteById(id);
    }
}
