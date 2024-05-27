package com.msfb.productrestapi.controller;

import com.msfb.productrestapi.dto.request.LoginRequest;
import com.msfb.productrestapi.dto.request.RegisterRequest;
import com.msfb.productrestapi.dto.response.CommonResponse;
import com.msfb.productrestapi.dto.response.LoginResponse;
import com.msfb.productrestapi.dto.response.RegisterResponse;
import com.msfb.productrestapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping(
            path = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<?>> register(@RequestBody RegisterRequest request) {
        RegisterResponse user = userService.register(request);
        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Registered user successfully")
                .data(user)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping(
            path = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<?>> login(@RequestBody LoginRequest request) {
        LoginResponse user = userService.login(request);
        CommonResponse<LoginResponse> response = CommonResponse.<LoginResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Login user successfully")
                .data(user)
                .build();
        return ResponseEntity.ok(response);
    }
}
