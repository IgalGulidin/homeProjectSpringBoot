package com.user_service.user_service.service;

import com.user_service.user_service.model.User;
import com.user_service.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public Long createUser(User user) {
        return userRepository.createUser(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public List<User> getAllUsers()  {
        return userRepository.getAllUsers();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteUser(id);
    }
}
