package com.msfb.productrestapi.service.impl;

import com.msfb.productrestapi.entity.Image;
import com.msfb.productrestapi.repository.ImageRepository;
import com.msfb.productrestapi.service.ImageService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository repository;
    private final Path directoryPath;

    public ImageServiceImpl(ImageRepository repository,@Value("${simple_api.multipart.path-location-image}") String directoryPath) {
        this.repository = repository;
        this.directoryPath = Paths.get(directoryPath);
    }

    @PostConstruct
    public void initDirectory() {
        if (!Files.exists(directoryPath)) {
            try {
                Files.createDirectory(directoryPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Image create(MultipartFile multipartFile) {
        try {
            if (!List.of("image/jpeg", "image/jpg", "image/svg", "image/png").contains(multipartFile.getContentType()))
                throw new Exception("Invalid image content type");
            String originalFilename = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            Path filePath = directoryPath.resolve(originalFilename);
            Files.copy(multipartFile.getInputStream(), filePath);

            Image image = Image.builder()
                    .name(originalFilename)
                    .path(filePath.toString())
                    .contentType(multipartFile.getContentType())
                    .size(multipartFile.getSize())
                    .build();

            return repository.saveAndFlush(image);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resource getById(String id) {
        try {
            Image image = repository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            Path filePath = Paths.get(image.getPath());
            if (!Files.exists(filePath))
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            return new UrlResource(filePath.toUri());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        try {
            Image image = repository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            Path filePath = Paths.get(image.getPath());
            if (!Files.exists(filePath))
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            Files.delete(filePath);
            repository.delete(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
