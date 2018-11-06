package com.ykbt.orm.controller;

import com.ykbt.orm.resource.UserResource;
import com.ykbt.orm.service.OrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/jpa/users")
public class JpaController {

    @Autowired
    @Qualifier("jpaServiceImpl")
    OrmService service;


    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserResource getUser(@PathVariable("id") Integer id) {
        return service.getUser(id);
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<UserResource> getUsers() {
        return service.getUsers();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserResource addUser(@RequestBody UserResource resource) {
        return service.addUser(resource);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserResource updateUser(@RequestBody UserResource resource) {
        return service.updateUser(resource);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserResource updateUser(@PathVariable("id") Integer id, @RequestBody UserResource resource) {
        resource.setId(id);
        return service.updateUser(resource);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleteUser(@PathVariable("id") Integer id) {
        service.deleteUser(id);
        return "OK";
    }
}
