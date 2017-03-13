package com.maksym.springboot.example.dao;

import com.maksym.springboot.example.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Maksym_Hridin on 2/28/2017.
 */
@Repository
public interface UserDao extends CrudRepository<User, Long> {
    List<User> findAll();
}
