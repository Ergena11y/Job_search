package com.example.job_search.service.impl;

import com.example.job_search.dao.UserDao;
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


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public User register(User user) {
        if (userDao.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Пользователь с email " + user.getEmail() + " уже существует");
        }
        return userDao.createUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getById(int id) {
        return userDao.getUserById(id)
                .orElseThrow(() -> new NoSuchElementException("Пользователь не найден с id: " + id));
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
    public List<User> getByName(String name) {
        return userDao.getUsersByName(name);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public Optional<User> getByPhoneNumber(String phone) {
        return userDao.getUserByPhoneNumber(phone);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userDao.existsByEmail(email);
    }
}
