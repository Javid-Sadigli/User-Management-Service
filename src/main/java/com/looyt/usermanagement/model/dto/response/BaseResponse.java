package com.looyt.usermanagement.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> 
{
    private boolean success; 
    private T data; 
    private int status; 
    private String message; 
}
