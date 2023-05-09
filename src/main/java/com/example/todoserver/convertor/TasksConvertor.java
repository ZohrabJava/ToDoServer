package com.example.todoserver.convertor;

import com.example.todoserver.dto.TaskDto;
import com.example.todoserver.entity.TasksEntity;
import com.example.todoserver.repository.TaskStatusRepository;
import com.example.todoserver.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TasksConvertor {
    private final UserRepository userRepository;
    private final TaskStatusRepository taskStatusRepository;

    public TaskDto taskEntityToDto(TasksEntity entity) {
        TaskDto taskDto = new TaskDto();
        taskDto.setTaskId(entity.getId());
        taskDto.setUsername(entity.getUser().getUserName());
        taskDto.setTitle(entity.getTitle());
        taskDto.setDescription(entity.getDescription());
        taskDto.setComment(entity.getComment());
        taskDto.setReviewerComment(entity.getReviewerComment());
        taskDto.setStartDate(entity.getStartDate());
        taskDto.setEndDate(entity.getEndDate());
        taskDto.setStatusId(entity.getStatus().getId());
        return taskDto;
    }

    public TasksEntity taskDtoToEntity(TaskDto taskDto) {
        TasksEntity taskEntity = new TasksEntity();
        taskEntity.setUser(userRepository.findByUserName(taskDto.getUsername()));
        taskEntity.setTitle(taskDto.getTitle());
        taskEntity.setDescription(taskDto.getDescription());
        taskEntity.setComment(taskDto.getComment());
        taskEntity.setReviewerComment(taskDto.getReviewerComment());
        taskEntity.setStartDate(taskDto.getStartDate());
        taskEntity.setEndDate(taskDto.getEndDate());
        taskEntity.setStatus(taskStatusRepository.getById(taskDto.getStatusId()));
        return taskEntity;
    }
}
