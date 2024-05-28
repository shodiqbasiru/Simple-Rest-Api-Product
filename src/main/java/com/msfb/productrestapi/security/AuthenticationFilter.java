package com.msfb.productrestapi.security;

import com.msfb.productrestapi.entity.JwtClaims;
import com.msfb.productrestapi.entity.User;
import com.msfb.productrestapi.service.AccountService;
import com.msfb.productrestapi.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final AccountService accountService;
    final String AUTH_HEADER = "Authorization";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        try {
            String bearerToken = request.getHeader(AUTH_HEADER);
            JwtClaims claimsByToken = jwtService.getClaimsByToken(bearerToken);
            User account = accountService.getByUserId(claimsByToken.getUserId());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    account.getUsername(),
                    null,
                    account.getAuthorities()
            );
            authentication.setDetails(WebApplicationInitializer.class);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            log.error("Cannot set user authenticate: {}", e.getMessage());
        }
        filterChain.doFilter(request, response);
    }
}
