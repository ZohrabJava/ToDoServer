package com.example.todoserver.repository;

import com.example.todoserver.entity.UserEntity;
import com.example.todoserver.entity.UserRoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRoleEntity, Integer> {
}
