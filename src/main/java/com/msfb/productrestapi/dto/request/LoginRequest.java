package com.msfb.productrestapi.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {
    private String emailOrPhone;
    private String password;
}
