package com.looyt.usermanagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.looyt.usermanagement.model.dto.request.UserRequest;
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

    @GetMapping
    public BaseResponse<List<UserResponse>> getAllUsers()
    {
        log.info(LOG_TEMPLATE, "GET", "");
        return userService.getAllUsers(); 
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<Void> createUser(@RequestBody UserRequest userRequest)
    {
        log.info(LOG_TEMPLATE, "POST", "");
        return userService.createUser(userRequest); 
    }

    @PutMapping("/{id}")
    public BaseResponse<Void> updateUser(
        @PathVariable long id, @RequestBody UserRequest userRequest)
    {
        log.info(LOG_TEMPLATE, "PUT", "/" + id);
        return userService.updateUser(id, userRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<Void> deleteUserById(@PathVariable long id)
    {
        log.info(LOG_TEMPLATE, "DELETE", "/" + id);
        return userService.deleteUserById(id); 
    }
}
