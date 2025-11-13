package com.looyt.usermanagement.service.impl;

import org.springframework.stereotype.Service;

import com.looyt.usermanagement.mapper.UserMapper;
import com.looyt.usermanagement.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl 
{
    private final UserRepository userRepository;  
    private final UserMapper userMapper; 
    
    
}
