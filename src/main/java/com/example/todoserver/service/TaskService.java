package com.example.todoserver.service;

import com.example.todoserver.convertor.TasksConvertor;
import com.example.todoserver.dto.TaskDto;
import com.example.todoserver.dto.TaskResponseDto;
import com.example.todoserver.entity.*;
import com.example.todoserver.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TasksConvertor tasksConvertor;
    private final UserRepository userRepository;
    private final TaskStatusRepository taskStatusRepository;
    private final RoleRepository roleRepository;


    public TaskResponseDto createTask(TaskDto taskDto) {
        TaskResponseDto responseDto = new TaskResponseDto();
        try {
            TasksEntity entity = tasksConvertor.taskDtoToEntity(taskDto);
            entity = taskRepository.save(entity);
        } catch (Exception e) {
            responseDto.setMessage("Incorrect input data or Db connection failure");
            return responseDto;
        }
        return responseDto;
    }

    public List<TaskDto> getTasksByUsernameAndStatus(TaskDto taskDto) {
        List<TaskDto> taskDtoList = new ArrayList<>();

        UserEntity user = userRepository.findByUserName(taskDto.getUsername());
        TaskStatusEntity status = taskStatusRepository.getById(taskDto.getStatusId());
        RoleEntity role = roleRepository.getById(1L);
        List<TasksEntity> tasksEntityList = new ArrayList<>();

        if (user.getRoles().contains(role)) {
            tasksEntityList = taskRepository.getAllByStatus(status);
        } else {
            tasksEntityList = taskRepository.getAllByUserAndStatus(user, status);
        }

        for (TasksEntity entity : tasksEntityList) {
            TaskDto dto = tasksConvertor.taskEntityToDto(entity);
            taskDtoList.add(dto);
        }

        return taskDtoList;
    }

    public TaskDto getTaskById(Long id) {
        TasksEntity entity = taskRepository.getById(id);
        return tasksConvertor.taskEntityToDto(entity);
    }

    public TaskResponseDto changeTaskStatus(TaskDto dto) {
        TaskResponseDto responseDto = new TaskResponseDto();
        if (dto.getStatusId() == null || dto.getTaskId() == null) {
            responseDto.setMessage("Invalid request");
            return responseDto;
        }
        TasksEntity entity = taskRepository.getById(dto.getTaskId());
        if (entity == null) {
            responseDto.setMessage("Task is not available");
            return responseDto;
        }
        TaskStatusEntity statusEntity = taskStatusRepository.getById(dto.getStatusId());
        if (statusEntity == null) {
            responseDto.setMessage("Task status is not available");
            return responseDto;
        }
        entity.setStatus(statusEntity);
        if (dto.getComment() != null) {
            entity.setComment(dto.getComment());
        }
        if (dto.getReviewerComment() != null) {
            entity.setReviewerComment(dto.getReviewerComment());
        }
        taskRepository.save(entity);

        return responseDto;
    }

    public List<TaskDto> getAllTasks() {
        List<TaskDto> taskDtoList = new ArrayList<>();

        Iterable<TasksEntity> tasksEntityList = taskRepository.findAll();

        for (TasksEntity entity : tasksEntityList) {
            TaskDto dto = tasksConvertor.taskEntityToDto(entity);
            taskDtoList.add(dto);
        }

        return taskDtoList;
    }
}
