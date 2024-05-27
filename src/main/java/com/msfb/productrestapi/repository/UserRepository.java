package com.msfb.productrestapi.repository;

import com.msfb.productrestapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findByEmail(String email);
    User findByPhoneNumber(String phoneNumber);
}
