package com.example.job_search.controller;



import com.example.job_search.model.User;
import com.example.job_search.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("register")
    public User register(@RequestBody  User user){
        return userService.register(user);
    }

    @GetMapping
    public List<User> getAll(){
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public User getById(@PathVariable int id){
        return userService.getById(id);
    }

    @GetMapping("search/name")
    public List<User> getByName(@RequestParam String name) {
        return userService.getByName(name);
    }

    @GetMapping("search/email")
    public Optional<User> getByEmail(@RequestParam String email) {
        return userService.getByEmail(email);
    }

    @GetMapping("search/phone")
    public Optional<User> getByPhone(@RequestParam String phone) {
        return userService.getByPhoneNumber(phone);
    }

    @GetMapping("exists")
    public boolean existsByEmail(@RequestParam String email) {
        return userService.existsByEmail(email);
    }

    @PostMapping("{id}/avatar")
    public void uploadAvatar(@PathVariable int id, MultipartFile file){
        userService.uploadAvatar(id, file);
    }
}
