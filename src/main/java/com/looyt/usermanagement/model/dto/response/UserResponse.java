package com.looyt.usermanagement.model.dto.response;

import com.looyt.usermanagement.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse 
{
    private long id; 
    private UserRole userRole; 
    private String fullName; 
    private String email; 
    private String phoneNumber;
}
