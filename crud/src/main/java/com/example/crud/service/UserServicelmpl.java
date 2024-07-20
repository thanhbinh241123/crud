package com.example.crud.service;


import com.example.crud.dao.UserRepository;
import com.example.crud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServicelmpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServicelmpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int userId) {
        Optional<User> result = userRepository.findById(userId);

        if (result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException("Did not find user id - " + userId);
        }
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(int userId) {
        userRepository.deleteById(userId);
    }
}
