package com.looyt.usermanagement.service;

import java.util.List;

import com.looyt.usermanagement.model.dto.response.BaseResponse;
import com.looyt.usermanagement.model.dto.response.UserResponse;

public interface UserService 
{
    public BaseResponse<UserResponse> getUserById(long userId); 
    public BaseResponse<List<UserResponse>> getAllUsers();
}
