package com.example.todoserver.repository;

import com.example.todoserver.entity.RoleEntity;
import com.example.todoserver.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    UserEntity findByUserName(String username);
    UserEntity findById(Long id);


}
