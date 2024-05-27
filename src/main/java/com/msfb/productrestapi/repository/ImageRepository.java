package com.msfb.productrestapi.repository;

import com.msfb.productrestapi.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<String, Image> {
}
