package com.example.todoserver.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TASKS")
@Getter
@Setter
public class TasksEntity {
    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserEntity user;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "comment")
    private String comment;

    @Column(name = "reviewer_comment")
    private String reviewerComment;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
    @JoinColumn(name = "task_status_id", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private TaskStatusEntity status;

    @Override
    public String toString() {
        return "TasksEntity{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", comment='" + comment + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                '}';
    }
}



