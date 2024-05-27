package com.msfb.productrestapi.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    private String skuCode;
    private String productName;
    private Integer qty;
    private Integer price;

    @JsonIgnore
    private MultipartFile image;
}
