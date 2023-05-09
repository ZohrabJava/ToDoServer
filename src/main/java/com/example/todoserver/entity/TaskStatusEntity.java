package com.example.todoserver.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "TASK_STATUS")
public class TaskStatusEntity {
    @Id
    @Column(name = "task_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status_name")
    private String status;

    @Override
    public String toString() {
        return "TaskStatusEntity{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
