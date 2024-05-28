package com.msfb.productrestapi.service.impl;

import com.msfb.productrestapi.entity.User;
import com.msfb.productrestapi.repository.UserRepository;
import com.msfb.productrestapi.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserRepository repository;

    @Override
    public User getByUserId(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
    }

    @Override
    public User getByContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return repository.findByEmail(authentication.getPrincipal().toString())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = repository.findByEmail(username);
        if(userOptional.isPresent()) {
            return userOptional.get();
        }

        userOptional = repository.findByPhoneNumber(username);
        if(userOptional.isPresent()) {
            return userOptional.get();
        }

        throw new UsernameNotFoundException("User not found with username or phone number : " + username);
    }
}
