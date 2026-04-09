package com.example.job_search.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("resumes")
public class ResumesController {
    public String resumes(Model model) {
        return "resumes";
    }
}
