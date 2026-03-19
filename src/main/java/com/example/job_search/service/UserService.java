package com.example.job_search.service;


import com.example.job_search.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User register(User user);
    List<User> getAllUsers();
    User  getById(int id);
    void uploadAvatar(int id, MultipartFile file);
    List<User> getByName (String name);
    Optional<User> getByEmail(String email);
    Optional<User> getByPhoneNumber(String phone);
    boolean existsByEmail(String email);
}
