package com.looyt.usermanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.looyt.usermanagement.model.dto.response.BaseResponse;
import com.looyt.usermanagement.model.dto.response.UserResponse;
import com.looyt.usermanagement.service.UserService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController 
{
    private static final String LOG_TEMPLATE = "{} request to /user{}";

    private final UserService userService; 

    @GetMapping("/{id}")
    public BaseResponse<UserResponse> getUserById(@PathVariable long id)
    {
        log.info(LOG_TEMPLATE, "GET", "/" + id);
        return userService.getUserById(id); 
    }
}
