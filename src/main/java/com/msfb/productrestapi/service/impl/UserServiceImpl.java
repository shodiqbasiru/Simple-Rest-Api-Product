package com.msfb.productrestapi.service.impl;

import com.msfb.productrestapi.dto.request.LoginRequest;
import com.msfb.productrestapi.dto.request.RegisterRequest;
import com.msfb.productrestapi.dto.response.LoginResponse;
import com.msfb.productrestapi.entity.User;
import com.msfb.productrestapi.repository.UserRepository;
import com.msfb.productrestapi.service.JwtService;
import com.msfb.productrestapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
    public User register(RegisterRequest request) {
        String hashcode = encoder.encode(request.getPassword());

        User payload = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(hashcode)
                .isEnable(true)
                .build();

        return repository.saveAndFlush(payload);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        String emailOrPhoneNumber = request.getEmailOrPhone();
        User user;

        if (emailOrPhoneNumber.contains("@")) {
            user = repository.findByEmail(emailOrPhoneNumber);
        } else {
            user = repository.findByPhoneNumber(emailOrPhoneNumber);
        }

        if (user == null || !encoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid email/phone number or password");
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user,
                request.getPassword()
        );
        Authentication authenticate = authenticationManager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        User principal = (User) authenticate.getPrincipal();
        String token = jwtService.generateToken(user);

        return LoginResponse.builder()
                .email(principal.getEmail())
                .token(token)
                .build();
    }
}
