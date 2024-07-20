package com.example.crud.service;

import com.example.crud.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(int userId);

    void save(User user);

    void deleteById(int userId);
}
