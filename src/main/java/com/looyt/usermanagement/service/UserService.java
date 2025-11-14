package com.looyt.usermanagement.service;

import com.looyt.usermanagement.model.dto.response.BaseResponse;
import com.looyt.usermanagement.model.dto.response.UserResponse;

public interface UserService 
{
    public BaseResponse<UserResponse> getUserById(long userId); 
}
