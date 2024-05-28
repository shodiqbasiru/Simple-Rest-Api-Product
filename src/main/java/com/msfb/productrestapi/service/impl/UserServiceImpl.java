package com.msfb.productrestapi.service.impl;

import com.msfb.productrestapi.dto.request.LoginRequest;
import com.msfb.productrestapi.dto.request.RegisterRequest;
import com.msfb.productrestapi.dto.response.LoginResponse;
import com.msfb.productrestapi.dto.response.RegisterResponse;
import com.msfb.productrestapi.dto.response.UserResponse;
import com.msfb.productrestapi.entity.User;
import com.msfb.productrestapi.repository.UserRepository;
import com.msfb.productrestapi.service.JwtService;
import com.msfb.productrestapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final JwtService jwtService;

    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterResponse register(RegisterRequest request) {
        String hashcode = encoder.encode(request.getPassword());

        User payload = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(hashcode)
                .isEnable(true)
                .build();

        User user = repository.saveAndFlush(payload);

        return RegisterResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.getEmailOrPhone(),
                request.getPassword()
        );

        Authentication authenticate = authenticationManager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        User principal = (User) authenticate.getPrincipal();
        String token = jwtService.generateToken(principal);

        return LoginResponse.builder()
                .email(principal.getEmail())
                .token(token)
                .build();
    }

    @Override
    public UserResponse findByEmail(String email) {
        User user = repository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    @Override
    public boolean validateToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = repository.findByEmail(authentication.getPrincipal().toString()).orElse(
                repository.findByPhoneNumber(authentication.getPrincipal().toString()).orElse(null)
        );
        return user != null;
    }
}
