package com.example.job_search.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping()
    public String  index(Model model){
        model.addAttribute("world", "World");
        return "index";
    }

    @GetMapping("layout")
    public  String layout(Model model) {
        return "layout";
    }
}
