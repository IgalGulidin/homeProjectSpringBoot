package com.user_service.user_service.service;

import com.user_service.user_service.model.User;

import java.util.List;

public interface UserService {
    Long createUser(User user);
    User getUserById(Long id);
    void updateUser(User user);
    List<User> getAllUsers();
    void deleteUser(Long id);
}
