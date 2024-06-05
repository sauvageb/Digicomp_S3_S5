package com.azqore.demo.api.mapper;

import com.azqore.demo.api.dto.UserDto;
import com.azqore.demo.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User fromDto(User assignedUser){
        return null;
    }

    public UserDto toDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

}
