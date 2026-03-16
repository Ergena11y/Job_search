package com.example.job_search.controller;



import com.example.job_search.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    @PostMapping("register")
    public User register(@RequestBody  User user){
        return null;
    }

    @GetMapping
    public List<User> getAll(){
        return List.of();
    }

    @GetMapping("{id}")
    public User getById(@PathVariable int id){
        return null;
    }

    @PostMapping("{id}/avatar")
    public void uploadAvatar(@PathVariable int id, MultipartFile file){}
}
