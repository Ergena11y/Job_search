package com.example.job_search.service.impl;

import com.example.job_search.dao.UserDao;
import com.example.job_search.dto.UserDto;
import com.example.job_search.exception.UserNotFoundException;
import com.example.job_search.model.User;
import com.example.job_search.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public UserDto register(User user) {
        if (userDao.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Пользователь с email " + user.getEmail() + " уже существует");
        }
        User savedUser = userDao.createUser(user);
        return convertToDto(savedUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userDao.getAllUsers().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getById(int id) throws UserNotFoundException {
       User user = userDao.getUserById(id)
                .orElseThrow(UserNotFoundException::new);

        return convertToDto(user);
    }

    @Override
    public void uploadAvatar(int id, MultipartFile file) {
        getById(id);

        String upload = "uploads/avatars/";
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path uploadPath = Paths.get(upload);

        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            file.transferTo(uploadPath.resolve(fileName).toFile());
            userDao.updateAvatar(id, upload + fileName);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<UserDto> getByName(String name) {
        return userDao.getUsersByName(name)
                .stream().map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getByEmail(String email) throws UserNotFoundException {
        User user = userDao.getUserByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    return convertToDto(user);
    }

    @Override
    public UserDto getByPhoneNumber(String phone) throws UserNotFoundException{
        User user = userDao.getUserByPhoneNumber(phone)
                .orElseThrow(UserNotFoundException::new);
        return  convertToDto(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userDao.existsByEmail(email);
    }

    @Override
    public UserDto findApplicant(String email) throws UserNotFoundException {
        User user = userDao.findApplicantByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        return convertToDto(user);
    }

    @Override
    public UserDto findEmployer(String email) throws  UserNotFoundException{
        User user = userDao.findEmployerByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        return convertToDto(user);
    }


    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setAvatar(user.getAvatar());
        dto.setAccountType(user.getAccountType());
        return dto;
    }
}
