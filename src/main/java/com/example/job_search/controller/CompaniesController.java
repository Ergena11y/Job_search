package com.example.job_search.controller;


import com.example.job_search.dto.UserDto;
import com.example.job_search.exception.UserNotFoundException;
import com.example.job_search.model.User;
import com.example.job_search.repository.UserRepository;
import com.example.job_search.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("companies")
@RequiredArgsConstructor
public class CompaniesController {
    private  final UserService userService;
    private final UserRepository userRepository;


    @GetMapping
    public String companies(@RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size,
                            Principal principal, Model model){
        Pageable pageable = PageRequest.of(page, size);
        Page<User> employersPage = userRepository.findByAccountType("EMPLOYER", pageable);

        model.addAttribute("companies", employersPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", employersPage.getTotalPages());
        model.addAttribute("totalItems", employersPage.getTotalElements());

        if (principal != null) {
            try {
                model.addAttribute("user", userService.getByEmail(principal.getName()));
            } catch (UserNotFoundException e) {
                model.addAttribute("user", null);
            }
        } else {
            model.addAttribute("user", null);
        }

        return "companies/companies";
    }

    @GetMapping("/{id}")
    public String companyProfile(@PathVariable int id, Model model, Principal principal) throws UserNotFoundException {
        UserDto company = userService.getById(id);
        model.addAttribute("company", company);

        if (principal != null) {
            try {
                model.addAttribute("user", userService.getByEmail(principal.getName()));
            } catch (UserNotFoundException e) {
                model.addAttribute("user", null);
            }
        }

        return "companies/company_profile";
    }
}
