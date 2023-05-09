package com.example.todoserver.dto;

import com.example.todoserver.entity.UserEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class UserDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;

    public static UserDto userEntityToDto(UserEntity user){
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        return userDto;
    }

    public static List<UserDto> userEntityListToDtoList(List<UserEntity> userEntityList){
        List<UserDto> userDtoList = new ArrayList<>();
        for (UserEntity userEntity : userEntityList) {
            UserDto user = new UserDto();
            user.setUserId(userEntity.getId());
            user.setUserName(userEntity.getUserName());
            user.setFirstName(userEntity.getFirstName());
            user.setLastName(userEntity.getLastName());
            userDtoList.add(user);
        }
        return userDtoList;
    }

    public static UserEntity userDtoToEntity(UserDto userDto){
        UserEntity user = new UserEntity();
        user.setUserName(userDto.getUserName());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
