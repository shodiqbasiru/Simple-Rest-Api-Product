package com.msfb.productrestapi.controller;

import com.msfb.productrestapi.service.ImageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "Image Api", description = "Image API")
public class ImageController {
    private final ImageService imageService;

    @GetMapping(path = "/api/products/image"+"/{imageId}")
    public ResponseEntity<?> downloadImage(@PathVariable String imageId){
        Resource resource=imageService.getById(imageId);
        String headerValue = String.format("attachment; filename=%s", resource.getFilename());
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }
}
