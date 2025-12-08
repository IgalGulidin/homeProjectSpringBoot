package com.user_service.user_service.repository;

import com.user_service.user_service.model.User;

import java.util.List;

public interface UserRepository {
    Long createUser(User user);
    void updateUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    void deleteUser(Long id);
    User getUserByEmail(String email);
}
