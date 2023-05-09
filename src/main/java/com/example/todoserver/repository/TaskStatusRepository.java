package com.example.todoserver.repository;

import com.example.todoserver.entity.TaskStatusEntity;
import com.example.todoserver.entity.TasksEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskStatusRepository extends CrudRepository<TaskStatusEntity, Integer> {
    TaskStatusEntity getById(Long id);
}
