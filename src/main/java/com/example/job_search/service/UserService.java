package com.example.job_search.service;


import com.example.job_search.dto.UserDto;
import com.example.job_search.exception.UserNotFoundException;
import com.example.job_search.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface UserService {
    UserDto register(User user);
    List<UserDto> getAllUsers();
    UserDto  getById(int id) throws  UserNotFoundException;
    void uploadAvatar(int id, MultipartFile file);
    List<UserDto> getByName (String name);
    UserDto getByEmail(String email) throws  UserNotFoundException;
    UserDto getByPhoneNumber(String phone) throws  UserNotFoundException;
    boolean existsByEmail(String email);

    UserDto findApplicant(String email) throws UserNotFoundException;

    UserDto findEmployer(String email) throws  UserNotFoundException;
}
