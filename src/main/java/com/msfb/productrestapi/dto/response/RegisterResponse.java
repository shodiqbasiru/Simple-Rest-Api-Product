package com.msfb.productrestapi.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
}
