package com.msfb.productrestapi.controller;

import com.msfb.productrestapi.dto.response.CommonResponse;
import com.msfb.productrestapi.dto.response.UserResponse;
import com.msfb.productrestapi.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Tag(name = "User Api", description = "user API")
public class UserController {

    private final UserService userService;

    @SecurityRequirement(name = "Authorization")
    @GetMapping(
            path = "/{email}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<UserResponse>> findByEmail (@PathVariable String email) {
        UserResponse user = userService.findByEmail(email);
        return ResponseEntity.ok(
                CommonResponse.<UserResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("User found")
                        .data(user)
                        .build()
        );
    }
}
