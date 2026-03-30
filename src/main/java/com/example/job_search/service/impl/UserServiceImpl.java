package com.example.job_search.service.impl;

import com.example.job_search.dao.UserDao;
import com.example.job_search.dto.UserDto;
import com.example.job_search.exception.AvatarImageNotFoundException;
import com.example.job_search.exception.UserNotFoundException;
import com.example.job_search.model.User;
import com.example.job_search.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto register(User user) {
        log.info("Регистрация нового пользователя с email: {}", user.getEmail());
        if (userDao.existsByEmail(user.getEmail())) {
            log.warn("Попытка регистрации с уже существующим email: {}", user.getEmail());
            throw new IllegalArgumentException("Пользователь с email " + user.getEmail() + " уже существует");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userDao.createUser(user);
        log.info("Пользователь успешно зарегистрирован с id: {}", savedUser.getId());
        return convertToDto(savedUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.debug("Получение списка всех пользователей");
        return userDao.getAllUsers().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getById(int id) throws UserNotFoundException {
        log.debug("Поиск пользователя по id: {}", id);
       User user = userDao.getUserById(id)
                .orElseThrow(UserNotFoundException::new);
        log.warn("Пользователь с id {} не найден", id);
        return convertToDto(user);
    }

    @Override
    public void uploadAvatar(int id, MultipartFile file) throws AvatarImageNotFoundException{
        log.info("Загрузка аватара для пользователя id: {}", id);
        String upload = "uploads/avatars/";
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path uploadPath = Paths.get(upload);

        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            file.transferTo(uploadPath.resolve(fileName).toFile());
            userDao.updateAvatar(id, upload + fileName);
            log.info("Аватар успешно загружен для пользователя id: {}", id);
        }catch (IOException e) {
            e.printStackTrace();
            log.error("Ошибка загрузки аватара для пользователя id {}: {}", id, e.getMessage());
        }
    }

    @Override
    public List<UserDto> getByName(String name) {
        log.debug("Поиск пользователей по имени: {}", name);
        return userDao.getUsersByName(name)
                .stream().map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getByEmail(String email) throws UserNotFoundException {
        log.debug("Поиск пользователя по email: {}", email);
        User user = userDao.getUserByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        log.warn("Пользователь с email {} не найден", email);
    return convertToDto(user);
    }

    @Override
    public UserDto getByPhoneNumber(String phone) throws UserNotFoundException{
        log.debug("Поиск пользователя по телефону: {}", phone);
        User user = userDao.getUserByPhoneNumber(phone)
                .orElseThrow(UserNotFoundException::new);
        log.warn("Пользователь с телефоном {} не найден", phone);
        return  convertToDto(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userDao.existsByEmail(email);
    }

    @Override
    public UserDto findApplicant(String email) throws UserNotFoundException {
        log.debug("Поиск соискателя по email: {}", email);
        User user = userDao.findApplicantByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        log.warn("Соискатель с email {} не найден", email);
        return convertToDto(user);
    }

    @Override
    public UserDto findEmployer(String email) throws  UserNotFoundException{
        log.debug("Поиск работодателя по email: {}", email);
        User user = userDao.findEmployerByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        log.warn("Работодатель с email {} не найден", email);
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
