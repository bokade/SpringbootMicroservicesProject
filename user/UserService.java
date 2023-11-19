package com.example.UserService.service;

import com.example.UserService.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService
{
    User saveUser(User user);

    List<User> getAllUser();

    User getUser(String userId);


}
