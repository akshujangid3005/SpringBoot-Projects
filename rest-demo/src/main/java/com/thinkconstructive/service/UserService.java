package com.thinkconstructive.service;

import com.thinkconstructive.entity.User;

import java.util.List;

public interface UserService {

    public String createUser(User user);
    public String updateUser(User user);
    public String deleteUser(int userId);
    public User getUser(int userId);
    public List<User> getAlluser();

  }
