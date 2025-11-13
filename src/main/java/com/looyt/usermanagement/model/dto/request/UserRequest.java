package com.looyt.usermanagement.model.dto.request;

import com.looyt.usermanagement.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest 
{
    private UserRole userRole; 
    private String fullName; 
    private String email; 
    private String phoneNumber;
}
