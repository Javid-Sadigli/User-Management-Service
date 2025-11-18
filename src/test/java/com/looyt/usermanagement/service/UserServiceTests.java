package com.looyt.usermanagement.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.mapstruct.factory.Mappers;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.looyt.usermanagement.enums.UserRole;
import com.looyt.usermanagement.exception.UserNotFoundException;
import com.looyt.usermanagement.mapper.UserMapper;
import com.looyt.usermanagement.model.dto.request.UserRequest;
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
        Assertions.assertTrue(serviceResponse.isSuccess());
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

    @Test
    public void givenGetAllUsers_WhenUsersFound_ThenReturnSuccessResponseWithUsers()
    {
        List<UserEntity> foundUserEntities = this.getTestUserEntities(); 
        List<UserResponse> expectedUserResponses = this.getTestUserResponses(); 

        int pageNumber = 0, pageSize = 2;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<UserEntity> foundUserPage = new PageImpl<>(foundUserEntities);

        Mockito.when(userRepository.findAll(pageable)).thenReturn(foundUserPage); 

        BaseResponse<List<UserResponse>> serviceResponse = userService
            .getAllUsers(pageNumber, pageSize); 

        Mockito.verify(userRepository, Mockito.times(1)).findAll(pageable); 
        Mockito.verifyNoMoreInteractions(userRepository); 

        Assertions.assertEquals(200, serviceResponse.getStatus());
        Assertions.assertTrue(serviceResponse.isSuccess());
        Assertions.assertIterableEquals(expectedUserResponses, serviceResponse.getData());
    }

    @Test
    public void givenGetAllUsers_WhenUsersNotFound_ThenReturnSuccessResponseWithEmptyList()
    {
        List<UserEntity> foundUserEntities = List.of(); 

        int pageNumber = 0, pageSize = 1;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<UserEntity> foundUserPage = new PageImpl<>(foundUserEntities);

        Mockito.when(userRepository.findAll(pageable)).thenReturn(foundUserPage); 

        BaseResponse<List<UserResponse>> serviceResponse = userService
            .getAllUsers(pageNumber, pageSize); 

        Mockito.verify(userRepository, Mockito.times(1)).findAll(pageable); 
        Mockito.verifyNoMoreInteractions(userRepository); 

        Assertions.assertEquals(200, serviceResponse.getStatus());
        Assertions.assertTrue(serviceResponse.isSuccess());
        Assertions.assertEquals(List.of(), serviceResponse.getData());
    }

    @Test
    public void givenCreateUser_ThenCreateUserAndReturnSuccessResponse()
    {
        UserRequest userRequest = this.getTestUserRequest1(); 
        UserEntity userEntityWithoutId = this.getTestUserEntity1WithoutId(); 
        UserEntity userEntity = this.getTestUserEntity1(); 

        Mockito.when(userRepository.save(userEntityWithoutId)).thenReturn(userEntity);

        BaseResponse<Void> serviceResponse = userService.createUser(userRequest); 

        Mockito.verify(userRepository, Mockito.times(1)).save(userEntityWithoutId); 
        Mockito.verifyNoMoreInteractions(userRepository);

        Assertions.assertTrue(serviceResponse.isSuccess());
        Assertions.assertEquals(201, serviceResponse.getStatus());
        Assertions.assertNull(serviceResponse.getData());
    }

    @Test
    public void givenUpdateUser_WhenUserIsFound_ThenUpdateUserAndReturnSuccessResponse()
    {
        long id = 2L; 
        UserRequest userRequest = this.getTestUserRequest1();
        UserEntity foundUserEntity = this.getTestUserEntity2(); 
        UserEntity updatedUserEntity = this.getTestUserEntity1WithDifferentId(); 
        UserResponse expectedUserResponse = this.getTestUserResponse1WithDifferentId(); 

        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(foundUserEntity)); 
        Mockito.when(userRepository.save(updatedUserEntity)).thenReturn(updatedUserEntity); 

        BaseResponse<UserResponse> serviceResponse = userService.updateUser(id, userRequest); 

        Mockito.verify(userRepository, Mockito.times(1)).findById(id); 
        Mockito.verify(userRepository, Mockito.times(1)).save(updatedUserEntity); 
    
        Assertions.assertEquals(200, serviceResponse.getStatus());
        Assertions.assertTrue(serviceResponse.isSuccess());
        Assertions.assertEquals(expectedUserResponse, serviceResponse.getData());
    }


    @Test
    public void givenUpdateUser_WhenUserIsNotFound_ThenThrowUserNotFoundException()
    {
        long id = 1L; 
        UserRequest userRequest = this.getTestUserRequest1(); 
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(
            UserNotFoundException.class, () -> userService.updateUser(id, userRequest));

        Mockito.verify(userRepository, Mockito.times(1)).findById(1L); 
        Mockito.verifyNoMoreInteractions(userRepository); 
    }

    @Test
    public void givenDeleteUserById_WhenUserIsFound_ThenDeleteItAndReturnSuccessResponse()
    {
        long id = 1L; 
        UserEntity foundUserEntity = this.getTestUserEntity1(); 

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(foundUserEntity)); 

        BaseResponse<Void> serviceResponse = userService.deleteUserById(id); 

        Mockito.verify(userRepository, Mockito.times(1)).findById(id);
        Mockito.verify(userRepository, Mockito.times(1)).delete(foundUserEntity);
        Mockito.verifyNoMoreInteractions(userRepository); 

        Assertions.assertEquals(200, serviceResponse.getStatus());
        Assertions.assertTrue(serviceResponse.isSuccess());
        Assertions.assertNull(serviceResponse.getData());
    }

    @Test
    public void givenDeleteUserById_WhenUserIsNotFound_ThenThrowUserNotFoundException()
    {
        long id = 1L; 
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(
            UserNotFoundException.class, () -> userService.deleteUserById(id));

        Mockito.verify(userRepository, Mockito.times(1)).findById(1L); 
        Mockito.verifyNoMoreInteractions(userRepository); 
    }

    private UserRequest getTestUserRequest1()
    {
        return UserRequest.builder()
            .email("example@mail.com")
            .fullName("Test User")
            .userRole(UserRole.EMPLOYEE)
            .build();
    }

    private UserEntity getTestUserEntity1()
    {
        return UserEntity.builder()
            .id(1L)
            .email("example@mail.com")
            .fullName("Test User")
            .userRole(UserRole.EMPLOYEE)
            .build(); 
    }
    private UserEntity getTestUserEntity2()
    {
        return UserEntity.builder()
            .id(2L)
            .email("example2@mail.com")
            .fullName("Test User 2")
            .userRole(UserRole.MANAGER)
            .build(); 
    }
    private UserEntity getTestUserEntity1WithoutId()
    {
        return UserEntity.builder()
            .email("example@mail.com")
            .fullName("Test User")
            .userRole(UserRole.EMPLOYEE)
            .build(); 
    }

    private UserEntity getTestUserEntity1WithDifferentId()
    {
        return UserEntity.builder()
            .id(2L)
            .email("example@mail.com")
            .fullName("Test User")
            .userRole(UserRole.EMPLOYEE)
            .build(); 
    }

    private UserResponse getTestUserResponse1()
    {
        return UserResponse.builder()
            .id(1L)
            .email("example@mail.com")
            .fullName("Test User")
            .userRole(UserRole.EMPLOYEE)
            .build(); 
    }

    private UserResponse getTestUserResponse2()
    {
        return UserResponse.builder()
            .id(2L)
            .email("example2@mail.com")
            .fullName("Test User 2")
            .userRole(UserRole.MANAGER)
            .build(); 
    }

    private UserResponse getTestUserResponse1WithDifferentId()
    {
        return UserResponse.builder()
            .id(2L)
            .email("example@mail.com")
            .fullName("Test User")
            .userRole(UserRole.EMPLOYEE)
            .build(); 
    }

    private List<UserEntity> getTestUserEntities() 
    {
        return List.of(getTestUserEntity1(), getTestUserEntity2());
    }

    private List<UserResponse> getTestUserResponses()
    {
        return List.of(getTestUserResponse1(), getTestUserResponse2()); 
    }
}
