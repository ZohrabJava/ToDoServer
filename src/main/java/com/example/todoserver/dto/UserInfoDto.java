package com.example.todoserver.dto;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class UserInfoDto {
    private int newTaskCount;
    private int inProgressTaskCount;
    private int inReviewTaskCount;
    private int rejectedTaskCount;
    private int doneTaskCount;
}
