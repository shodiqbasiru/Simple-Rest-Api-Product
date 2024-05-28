package com.msfb.productrestapi.service;

import com.msfb.productrestapi.dto.request.LoginRequest;
import com.msfb.productrestapi.dto.request.RegisterRequest;
import com.msfb.productrestapi.dto.response.LoginResponse;
import com.msfb.productrestapi.dto.response.RegisterResponse;
import com.msfb.productrestapi.dto.response.UserResponse;

public interface UserService {
    RegisterResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
    UserResponse findByEmail(String email);
    boolean validateToken();
}
