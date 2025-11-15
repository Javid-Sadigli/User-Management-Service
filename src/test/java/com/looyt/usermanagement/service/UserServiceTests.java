package com.looyt.usermanagement.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.mapstruct.factory.Mappers;

import com.looyt.usermanagement.enums.UserRole;
import com.looyt.usermanagement.exception.UserNotFoundException;
import com.looyt.usermanagement.mapper.UserMapper;
import com.looyt.usermanagement.model.dto.response.BaseResponse;
import com.looyt.usermanagement.model.dto.response.UserResponse;
import com.looyt.usermanagement.model.entity.UserEntity;
import com.looyt.usermanagement.repository.UserRepository;
import com.looyt.usermanagement.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests 
{
    private UserService userService; 
    private UserRepository userRepository; 
    private UserMapper userMapper; 

    @BeforeEach
    public void setUp()
    {
        userRepository = Mockito.mock(UserRepository.class); 
        userMapper = Mappers.getMapper(UserMapper.class); 
        userService = new UserServiceImpl(userRepository, userMapper); 
    }
    
    @Test
    public void givenGetUserById_WhenUserIsFound_ThenReturnSuccessResponseWithUser()
    {
        long id = 1L; 
        UserEntity foundUserEntity = this.getTestUserEntity1(); 
        UserResponse expectedUserResponse = this.getTestUserResponse1();

        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(foundUserEntity)); 
        
        BaseResponse<UserResponse> serviceResponse = userService.getUserById(id); 
        
        Mockito.verify(userRepository, Mockito.times(1)).findById(id); 
        Mockito.verifyNoMoreInteractions(userRepository);

        Assertions.assertEquals(200, serviceResponse.getStatus());
        Assertions.assertEquals(true, serviceResponse.isSuccess());
        Assertions.assertEquals(expectedUserResponse, serviceResponse.getData());
    }

    @Test
    public void givenGetUserById_WhenUserIsNotFound_ThenThrowUserNotFoundException()
    {
        long id = 1L; 
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(
            UserNotFoundException.class, () -> userService.getUserById(id));

        Mockito.verify(userRepository, Mockito.times(1)).findById(1L); 
        Mockito.verifyNoMoreInteractions(userRepository); 
    }

    private UserEntity getTestUserEntity1()
    {
        return UserEntity.builder()
            .id(1L)
            .email("example@mail.com")
            .fullName("Test User").userRole(UserRole.EMPLOYEE)
            .build(); 
    }

    private UserResponse getTestUserResponse1()
    {
        return UserResponse.builder()
            .id(1L)
            .email("example@mail.com")
            .fullName("Test User").userRole(UserRole.EMPLOYEE)
            .build(); 
    }
}
