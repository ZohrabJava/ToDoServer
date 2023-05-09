package com.example.todoserver.repository;

import com.example.todoserver.entity.RoleEntity;
import com.example.todoserver.entity.TaskStatusEntity;
import com.example.todoserver.entity.TasksEntity;
import com.example.todoserver.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<TasksEntity, Integer> {
    List<TasksEntity> getAllByUserAndStatus(UserEntity user, TaskStatusEntity status);
    List<TasksEntity> getAllByStatus(TaskStatusEntity status);
    TasksEntity getById(Long id);
}
