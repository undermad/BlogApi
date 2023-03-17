package com.ectimel.blogspringbootrestapi.service;

import com.ectimel.blogspringbootrestapi.payload.LoginDto;
import com.ectimel.blogspringbootrestapi.payload.RegisterDto;

public interface AuthService {
     String login(LoginDto loginDto);
     String register(RegisterDto registerDto);
}
