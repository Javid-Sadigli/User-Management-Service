package com.looyt.usermanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.looyt.usermanagement.model.dto.request.UserRequest;
import com.looyt.usermanagement.model.dto.response.UserResponse;
import com.looyt.usermanagement.model.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper 
{
    public UserResponse entityToResponse(UserEntity entity);
    
    @Mapping(target = "id", ignore = true)
    public UserEntity requestToEntity(UserRequest request); 

    @Mapping(target = "id", ignore = true)
    public void convertRequestToEntity(UserRequest request, @MappingTarget UserEntity entity); 
}
