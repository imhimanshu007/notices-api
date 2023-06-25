package com.relaxcoder.noticesapi.service;

import com.relaxcoder.noticesapi.dtos.LoginDto;
import com.relaxcoder.noticesapi.dtos.RegisterDto;

public interface UserService {
    String loginUser(LoginDto loginDto);
    String registerUser(RegisterDto registerDto);

}
