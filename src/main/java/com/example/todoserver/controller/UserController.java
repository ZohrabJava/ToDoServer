package com.example.todoserver.controller;

import com.example.todoserver.dto.UserDto;
import com.example.todoserver.dto.UserInfoDto;
import com.example.todoserver.dto.UserResponseDto;
import com.example.todoserver.service.UserService;
import com.nimbusds.openid.connect.sdk.claims.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/example")
    public List<String> getExample() {
        return Arrays.asList("example1", "example2", "example3");
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/user/get-all")
    public List<UserDto> getAllEmployees() {
        var dtoList = UserDto.userEntityListToDtoList(service.getAllEmployees());
        return dtoList;
    }

    @PostMapping("/user/register")
    public UserResponseDto save(@RequestBody UserDto employee) {
        return service.save(employee);
    }

    @PostMapping("/user/getUsername")
    public UserDto getUserByUsername(@RequestBody UserDto user) {
        var userDto = UserDto.userEntityToDto(service.getUserByUsername(user.getUserName()));
        return userDto;
    }
    @GetMapping("/getUser/{username}")
    public UserDto getUserByUsername(@PathVariable("username") String username) {
        var userDto = UserDto.userEntityToDto(service.getUserByUsername(username));
        return userDto;
    }

    @GetMapping("/user/getAllUsers")
    public List<UserDto> getAllUsers() {
        var dtoList = UserDto.userEntityListToDtoList(service.getAllUsers());
        return dtoList;
    }

    @GetMapping("/user/userInfo/{username}")
    public UserInfoDto getUserInfoByUsername(@PathVariable("username") String username) {
        var userInfo = service.getUserInfoByUsername(username);
        return userInfo;
    }

    @GetMapping("/user/{id}")
    public UserDto getUserById(@PathVariable("id") Long id) {
        var userDto = UserDto.userEntityToDto(service.getUserById(id));
        return userDto;
    }

}
