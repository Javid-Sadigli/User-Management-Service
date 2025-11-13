package com.looyt.usermanagement.mapper;

import org.mapstruct.Mapper;

import com.looyt.usermanagement.model.dto.request.UserRequest;
import com.looyt.usermanagement.model.dto.response.UserResponse;
import com.looyt.usermanagement.model.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper 
{
    public UserResponse entityToResponse(UserEntity entity); 
    public UserEntity requestToEntity(UserRequest request); 
}
