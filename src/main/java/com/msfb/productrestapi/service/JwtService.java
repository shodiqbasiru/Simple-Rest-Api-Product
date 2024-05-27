package com.msfb.productrestapi.service;

import com.msfb.productrestapi.entity.JwtClaims;
import com.msfb.productrestapi.entity.User;

public interface JwtService {
    String generateToken(User user);
    JwtClaims getClaimsByToken(String token);
}
