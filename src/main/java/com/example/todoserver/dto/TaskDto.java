package com.example.todoserver.dto;

import com.example.todoserver.entity.TasksEntity;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TaskDto {
    private Long taskId;
    private String username;
    private String title;
    private String description;
    private String comment;
    private String reviewerComment;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long statusId;


}
