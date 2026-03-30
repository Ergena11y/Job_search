package com.example.job_search.controller;



import com.example.job_search.dto.UserDto;
import com.example.job_search.exception.UserNotFoundException;
import com.example.job_search.model.User;
import com.example.job_search.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }

    @GetMapping("/applicant/{email}")
    public ResponseEntity<UserDto> findApplicant(@PathVariable String email) throws UserNotFoundException {
        return ResponseEntity.ok(userService.findApplicant(email));
    }

    @GetMapping("/employer/{email}")
    public ResponseEntity<UserDto> findEmployer(@PathVariable String email) throws UserNotFoundException {
        return ResponseEntity.ok(userService.findEmployer(email));
    }

    @GetMapping
    public List<UserDto> getAll(){
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getById(@PathVariable int id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("search/name")
    public ResponseEntity<List<UserDto>> getByName(@RequestParam String name) {
        return ResponseEntity.ok(userService.getByName(name));
    }

    @GetMapping("search/email")
    public ResponseEntity<UserDto> getByEmail(@RequestParam String email) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getByEmail(email));
    }

    @GetMapping("search/phone")
    public ResponseEntity<UserDto> getByPhone(@RequestParam String phone) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getByPhoneNumber(phone));
    }

    @GetMapping("exists")
    public ResponseEntity<Boolean> existsByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.existsByEmail(email));
    }

    @PostMapping("/{id}/avatar")
    public ResponseEntity<Void> uploadAvatar( @PathVariable int id,@RequestParam("file") MultipartFile file) throws AvatarImageNotFoundException {
        userService.uploadAvatar(id, file);
        return ResponseEntity.ok().build();
    }
}
