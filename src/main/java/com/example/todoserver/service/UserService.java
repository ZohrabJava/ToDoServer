package com.example.todoserver.service;

import com.example.todoserver.dto.TaskDto;
import com.example.todoserver.dto.UserDto;
import com.example.todoserver.dto.UserInfoDto;
import com.example.todoserver.dto.UserResponseDto;
import com.example.todoserver.entity.RoleEntity;
import com.example.todoserver.entity.UserEntity;
import com.example.todoserver.entity.UserRoleEntity;
import com.example.todoserver.repository.RoleRepository;
import com.example.todoserver.repository.TaskRepository;
import com.example.todoserver.repository.UserRepository;
import com.example.todoserver.repository.UserRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserRoleRepository userRoleRepository;
    private TaskService taskService;

    private final PasswordEncoder passwordEncoder;

    public UserResponseDto save(UserDto userDto) {
        UserResponseDto response = new UserResponseDto();
        if (getAllUsername().contains(userDto.getUserName())) {
            response.setMessage("Duplicate username");
            return response;
        }
        try {
            UserEntity user = UserDto.userDtoToEntity(userDto);
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            user = userRepository.save(user);
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setUser(user);
            userRoleEntity.setRole(roleRepository.getById(2L));
            userRoleRepository.save(userRoleEntity);

        } catch (Exception e) {
            response.setMessage("Incorrect input data or Db connection failure");
            return response;
        }

        return response;
    }

    public List<UserEntity> getAllEmployees() {
        List<UserEntity> employees = new ArrayList<>();
        Streamable.of(userRepository.findAll())
                .forEach(employees::add);
        return employees;
    }

    public List<UserEntity> getAllUsers() {
        List<UserEntity> allUsers = new ArrayList<>();
        RoleEntity role = roleRepository.getById(1L);
        Streamable.of(userRepository.findAll())
                .forEach(allUsers::add);
        allUsers.removeIf(o-> o.getRoles().contains(role));
        return allUsers;
    }

    public UserEntity getUserByUsername(String userName) {
        UserEntity user = userRepository.findByUserName(userName);
        return user;
    }

    public UserEntity getUserById(Long id){
        UserEntity user = userRepository.findById(id);
        return user;
    }

    public void delete(int employeeId) {
        userRepository.deleteById(employeeId);
    }

    public Set<String> getAllUsername() {
        List<UserEntity> employees = getAllEmployees();
        Set<String> usernames = new HashSet<>();
        for (UserEntity employee : employees) {
            usernames.add(employee.getUserName());
        }
        return usernames;
    }
    public UserInfoDto getUserInfoByUsername(String username){
        UserInfoDto userInfoDto = new UserInfoDto();
        TaskDto taskDto = new TaskDto();
        taskDto.setUsername(username);
        taskDto.setStatusId(1L);
        userInfoDto.setNewTaskCount(taskService.getTasksByUsernameAndStatus(taskDto).size());
        taskDto.setStatusId(2L);
        userInfoDto.setInProgressTaskCount(taskService.getTasksByUsernameAndStatus(taskDto).size());
        taskDto.setStatusId(3L);
        userInfoDto.setInReviewTaskCount(taskService.getTasksByUsernameAndStatus(taskDto).size());
        taskDto.setStatusId(4L);
        userInfoDto.setRejectedTaskCount(taskService.getTasksByUsernameAndStatus(taskDto).size());
        taskDto.setStatusId(5L);
        userInfoDto.setDoneTaskCount(taskService.getTasksByUsernameAndStatus(taskDto).size());
        return userInfoDto;
    }
}
