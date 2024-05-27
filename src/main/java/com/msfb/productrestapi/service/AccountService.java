package com.msfb.productrestapi.service;

import com.msfb.productrestapi.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {
    User getByUserId(String id);
    User getByContext();
}
