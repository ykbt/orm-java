package com.ykbt.orm.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ykbt.orm.entity.UserEntity;
import com.ykbt.orm.mapper.MyBatisMapper;
import com.ykbt.orm.resource.UserResource;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class MybatisServiceImpl implements OrmService {

    @Autowired
    private MyBatisMapper mapper;

    public List<UserResource> getUsers() {
        List<UserEntity> entities = mapper.findAll();
        List<UserResource> resources = new ArrayList<>();
        entities.stream().forEach(entity -> {
            UserResource resource = new UserResource();
            BeanUtils.copyProperties(entity, resource);
            resources.add(resource);
        });
        return resources;
    }

    public UserResource getUser(Integer id) {
        if(id == null){
            return null;
        }

        UserEntity entity = mapper.get(id);
        UserResource resource = new UserResource();
        BeanUtils.copyProperties(entity, resource);

        return resource;
    }

    @Transactional
    public UserResource addUser(UserResource resource) {
        if (resource == null || resource.getId() != null) {
            return null;
        }

        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(resource, entity);

        if (mapper.save(entity) != 1){
            throw new RuntimeException("insertに失敗しました Entity:" + entity);
        }

        UserEntity addedEntity = mapper.get(entity.getId());
        UserResource addedResouce = new UserResource();
        BeanUtils.copyProperties(addedEntity, addedResouce);

        return addedResouce;
    }

    //saveメソッドは INSERT or UPDATE
    @Transactional
    public UserResource updateUser(UserResource resource) {
        if (resource == null || resource.getId() == null) {
            return null;
        }

        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(resource, entity);

        mapper.update(entity);
        UserEntity updatedEntity = mapper.get(entity.getId());
        UserResource updatedResource = new UserResource();
        BeanUtils.copyProperties(updatedEntity, updatedResource);

        return updatedResource;
    }

    @Transactional
    public void deleteUser(Integer id) {
        mapper.delete(id);
    }
}
