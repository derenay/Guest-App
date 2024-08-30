package com.example.guest.service;

import com.example.guest.model.UserModel;

public interface UserService {
    UserModel findByUsername(String username);
    UserModel saveUser(UserModel user);
}
