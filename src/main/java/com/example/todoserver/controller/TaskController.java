package com.example.todoserver.controller;

import com.example.todoserver.dto.TaskDto;
import com.example.todoserver.dto.TaskResponseDto;
import com.example.todoserver.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    @Autowired
    private TaskService service;

    @PostMapping("/task/create")
    public TaskResponseDto save(@RequestBody TaskDto taskDto) {
        return service.createTask(taskDto);
    }

    @GetMapping("/task/getTasksByUsernameAndStatus/{username}/{status}")
    public List<TaskDto> getTasksByUsernameAndStatus(@PathVariable("username") String username, @PathVariable("status") Long status) {
        TaskDto taskDto = new TaskDto();
        taskDto.setUsername(username);
        taskDto.setStatusId(status);
        return service.getTasksByUsernameAndStatus(taskDto);
    }
    @GetMapping("/task/getTasksById/{taskId}")
    public TaskDto getTasksByUsernameAndStatus(@PathVariable("taskId") Long taskId) {
        return service.getTaskById(taskId);
    }

    @PostMapping("/task/changeTaskStatus")
    public TaskResponseDto changeTaskStatus(@RequestBody TaskDto taskDto) {
        return service.changeTaskStatus(taskDto);
    }
    @GetMapping("/task/getAllTasks")
    public List<TaskDto> getAllTasks() {
        return service.getAllTasks();
    }
}
