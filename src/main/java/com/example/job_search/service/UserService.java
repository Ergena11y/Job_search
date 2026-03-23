package com.example.job_search.service;


import com.example.job_search.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto register(UserDto user);
    List<UserDto> getAllUsers();
    UserDto  getById(int id);
    void uploadAvatar(int id, MultipartFile file);
    List<UserDto> getByName (String name);
    Optional<UserDto> getByEmail(String email);
    Optional<UserDto> getByPhoneNumber(String phone);
    boolean existsByEmail(String email);
}
