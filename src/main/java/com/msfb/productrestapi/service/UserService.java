package com.msfb.productrestapi.service;

import com.msfb.productrestapi.dto.request.LoginRequest;
import com.msfb.productrestapi.dto.request.LoginPhoneRequest;
import com.msfb.productrestapi.dto.request.RegisterRequest;
import com.msfb.productrestapi.dto.response.LoginResponse;
import com.msfb.productrestapi.dto.response.RegisterResponse;

public interface UserService {
    RegisterResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}
