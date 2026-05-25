package com.example.job_search.controller;

import com.example.job_search.dto.MessageDto;
import com.example.job_search.model.RespondedApplicants;
import com.example.job_search.repository.RespondedApplicantsRepository;
import com.example.job_search.service.MessageService;
import com.example.job_search.service.UserService;
import com.example.job_search.service.impl.ErrorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final MessageService messageService;
    private final RespondedApplicantsRepository respondedApplicantsRepository;
    private final UserService userService;
    private final ErrorServiceImpl errorServiceImpl;

    @GetMapping("/{respondedId}")
    public String chatPage(@PathVariable int respondedId,
                           Principal principal,
                           Model model){
        RespondedApplicants ra = respondedApplicantsRepository.findById(respondedId)
                .orElseThrow(() -> new RuntimeException("Not found"));

        if (!"APPROVED".equals(ra.getStatus())){
            return "redirect:/vacancies";
        }

        String email = principal.getName();
        String applicantEmail = ra.getResume().getApplicant().getEmail();
        String employerEmail = ra.getVacancy().getAuthor().getEmail();

        if (!email.equals(applicantEmail) && !email.equals(employerEmail)){
            return "redirect:/vacancies";
        }

        List<MessageDto> messages = messageService.getByRespondedId(respondedId);

        model.addAttribute("ra", ra);
        model.addAttribute("messages", messages);
        model.addAttribute("respondedId", respondedId);
        model.addAttribute("user", userService.getByEmail(email));

        boolean isApplicant = email.equals(applicantEmail);
        model.addAttribute("isApplicant", isApplicant);
        model.addAttribute("interlocutor", isApplicant ? ra.getVacancy().getAuthor() : ra.getResume().getApplicant());
        model.addAttribute("vacancy", ra.getVacancy());

        return "chat/chat";
    }
}
