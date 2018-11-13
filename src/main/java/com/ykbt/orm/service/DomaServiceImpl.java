package com.ykbt.orm.service;

import com.ykbt.orm.dao.DomaDao;
import com.ykbt.orm.entity.UserEntity;
import com.ykbt.orm.resource.UserResource;
import org.seasar.doma.Dao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class DomaServiceImpl implements OrmService {
    @Autowired
    private DomaDao dao;

    public List<UserResource> getUsers() {
        List<UserEntity> entities = dao.findAll();
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

        UserEntity entity = dao.get(id);
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

        int resultCount = dao.save(entity);

        if (resultCount != 1){
            throw new RuntimeException("insertに失敗しました Entity:" + entity);
        }

        UserEntity addedEntity = dao.get(entity.getId());
        UserResource addedResouce = new UserResource();
        BeanUtils.copyProperties(addedEntity, addedResouce);

        return addedResouce;
    }

    @Transactional
    public UserResource updateUser(UserResource resource) {
        if (resource == null || resource.getId() == null) {
            return null;
        }

        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(resource, entity);

        dao.update(entity);

        UserEntity updatedEntity = dao.get(entity.getId());
        UserResource updatedResource = new UserResource();
        BeanUtils.copyProperties(updatedEntity, updatedResource);

        return updatedResource;
    }

    @Transactional
    public void deleteUser(Integer id) {
        dao.delete(id);
    }
}
