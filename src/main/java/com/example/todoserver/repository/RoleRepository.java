package com.example.todoserver.repository;

import com.example.todoserver.entity.RoleEntity;
import com.example.todoserver.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository  extends CrudRepository<RoleEntity, Integer> {
    RoleEntity getById(Long id);
}
