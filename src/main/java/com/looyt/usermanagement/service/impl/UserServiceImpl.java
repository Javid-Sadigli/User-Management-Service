package com.looyt.usermanagement.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.looyt.usermanagement.exception.UserNotFoundException;
import com.looyt.usermanagement.mapper.UserMapper;
import com.looyt.usermanagement.model.dto.request.UserRequest;
import com.looyt.usermanagement.model.dto.response.BaseResponse;
import com.looyt.usermanagement.model.dto.response.UserResponse;
import com.looyt.usermanagement.model.entity.UserEntity;
import com.looyt.usermanagement.repository.UserRepository;
import com.looyt.usermanagement.service.UserService;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;  
    private final UserMapper userMapper; 
    
    public BaseResponse<UserResponse> getUserById(long userId)
    {
        UserEntity userEntity = this.getUserEntityById(userId); 
        UserResponse userResponse = userMapper.entityToResponse(userEntity); 

        log.info("Found user: {}", userResponse);

        return BaseResponse.<UserResponse>builder()
            .message("User was found")
            .status(HttpStatus.OK.value())
            .success(true)
            .data(userResponse)
            .build(); 
    }
    
    public BaseResponse<List<UserResponse>> getAllUsers()
    {
        List<UserEntity> userEntities = userRepository.findAll();

        List<UserResponse> userResponses = userEntities.stream()
            .map(userMapper::entityToResponse).collect(Collectors.toList());

        log.info("Retrieved all users.");

        return BaseResponse.<List<UserResponse>>builder()
            .message("Retrieved all users")
            .status(HttpStatus.OK.value())
            .success(true)
            .data(userResponses)
            .build(); 
    }

    @Transactional
    public BaseResponse<Void> createUser(UserRequest userRequest)
    {
        UserEntity userEntity = userMapper.requestToEntity(userRequest); 
        userEntity = userRepository.save(userEntity); 

        log.info("Created new user: {}", userEntity);

        return BaseResponse.<Void>builder()
            .message("User was created")
            .status(HttpStatus.CREATED.value())
            .success(true)
            .build(); 
    }
    
    @Transactional
    public BaseResponse<Void> updateUser(long userId, UserRequest userRequest)
    {
        UserEntity userEntity = this.getUserEntityById(userId); 
        userMapper.convertRequestToEntity(userRequest, userEntity);
        userEntity = userRepository.save(userEntity); 

        log.info("Updated user: {}", userEntity);

        return BaseResponse.<Void>builder()
            .message("User was updated")
            .status(HttpStatus.OK.value())
            .success(true)
            .build(); 
    }

    @Transactional
    public BaseResponse<Void> deleteUserById(long userId)
    {
        UserEntity userEntity = this.getUserEntityById(userId); 
        userRepository.delete(userEntity);

        log.info("Deleted user with id: {}", userId);
    
        return BaseResponse.<Void>builder()
            .message("User was deleted")
            .status(HttpStatus.NO_CONTENT.value())
            .success(true)
            .build(); 
    }

    private UserEntity getUserEntityById(long userId)
    {
        return userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User was not found with id: " + userId));
    }
}
