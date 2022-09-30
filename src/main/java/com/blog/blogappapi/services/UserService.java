package com.blog.blogappapi.services;

import com.blog.blogappapi.entities.User;
import com.blog.blogappapi.payloads.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user , Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
}
