package com.maksym.springboot.example.service;

import com.maksym.springboot.example.acl.PermissionRules;
import com.maksym.springboot.example.dao.UserDao;
import com.maksym.springboot.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Maksym_Hridin on 2/28/2017.
 */
@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @PreAuthorize(PermissionRules.CREATE_CONDITION)
    public User create(User user) {
        return userDao.save(user);
    }

    public void clear() {
        userDao.deleteAll();
    }

    public List<User> readAll() {
        return userDao.findAll();
    }
}
