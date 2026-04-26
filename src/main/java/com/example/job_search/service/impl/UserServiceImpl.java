package com.example.job_search.service.impl;


import com.example.job_search.dto.UpdateProfileDto;
import com.example.job_search.dto.UserDto;
import com.example.job_search.exception.AvatarImageNotFoundException;
import com.example.job_search.exception.UserNotFoundException;
import com.example.job_search.exception.UserProfileNotFoundException;
import com.example.job_search.model.User;
import com.example.job_search.repository.UserRepository;
import com.example.job_search.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto register(User user) {
        log.info("Регистрация нового пользователя с email: {}", user.getEmail());
        if (userRepository.existsByEmail(user.getEmail())) {
            log.warn("Попытка регистрации с уже существующим email: {}", user.getEmail());
            throw new IllegalArgumentException("Пользователь с email " + user.getEmail() + " уже существует");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        User saved = userRepository.save(user);
        log.info("Пользователь успешно зарегистрирован с id: {}", saved.getId());
        return convertToDto(saved);
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.debug("Получение списка всех пользователей");
        return userRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getById(int id) throws UserNotFoundException {
        log.debug("Поиск пользователя по id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        log.warn("Пользователь с id {} не найден", id);
        return convertToDto(user);
    }

    @Override
    public String uploadAvatar(int id, MultipartFile file) throws AvatarImageNotFoundException {
        log.info("Загрузка аватара для пользователя id: {}", id );

        String upload = "uploads/avatars/";

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path uploadPath = Paths.get(upload);

        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            file.transferTo(uploadPath.resolve(fileName).toFile());


            return upload + fileName;
        } catch (IOException e) {
            log.error("Ошибка загрузки аватара: {}", e.getMessage());
            throw  new RuntimeException();
        }
    }

    @Override
    public List<UserDto> getByName(String name) {
        log.debug("Поиск пользователей по имени: {}", name);

        return userRepository.findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(name, name)
                .stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getByEmail(String email) throws UserNotFoundException {
        log.debug("Поиск пользователя по email: {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        log.warn("Пользователь с email {} не найден", email);
    return convertToDto(user);
    }

    @Override
    public UserDto getByPhoneNumber(String phone) throws UserNotFoundException{
        log.debug("Поиск пользователя по телефону: {}", phone);
        User user = userRepository.findByPhoneNumber(phone)
                .orElseThrow(UserNotFoundException::new);
        log.warn("Пользователь с телефоном {} не найден", phone);
        return  convertToDto(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDto findApplicant(String email) throws UserNotFoundException {
        return convertToDto(userRepository.findByEmailAndAccountType(email, "APPLICANT")
                .orElseThrow(UserNotFoundException::new));
    }

    @Override
    public UserDto findEmployer(String email) throws  UserNotFoundException{
       return convertToDto(
               userRepository.findByEmailAndAccountType(email, "EMPLOYER")
                       .orElseThrow(UserNotFoundException::new));
    }

    @Override
    public void updateUserProfile(int userId, UpdateProfileDto dto, MultipartFile avatar) throws UserProfileNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserProfileNotFoundException("Профиль не найден"));

        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPhoneNumber(dto.getPhoneNumber());

        if (avatar != null && !avatar.isEmpty()){
            try {
                String avatarPath = uploadAvatar(userId, avatar);
                user.setAvatar(avatarPath);
            } catch (AvatarImageNotFoundException e){
                log.error("error avatar");
                throw new RuntimeException(e);
            }
        }
        userRepository.save(user);
    }

    @Override
    public int getUserIdByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new)
                .getId();
    }


    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setAvatar(user.getAvatar());
        dto.setAccountType(user.getAccountType());
        return dto;
    }
}
