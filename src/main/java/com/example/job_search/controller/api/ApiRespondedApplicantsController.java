//package com.example.job_search.controller.api;
//
//
//
//import com.example.job_search.dto.RespondedDto;
//import com.example.job_search.dto.UserDto;
//import com.example.job_search.exception.ForbiddenException;
//import com.example.job_search.exception.UserNotFoundException;
//import com.example.job_search.model.RespondedApplicants;
//import com.example.job_search.model.Resumes;
//import com.example.job_search.repository.ResumeRepository;
//import com.example.job_search.service.RespondedApplicantsService;
//import com.example.job_search.service.UserService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.security.Principal;
//import java.util.List;
//
//@RestController
//@RequestMapping("api/responds")
//@RequiredArgsConstructor
//public class ApiRespondedApplicantsController {
//
//    private final RespondedApplicantsService respondedApplicantsService;
//    private final UserService userService;
//    private final ResumeRepository resumeRepository;
//
//    @GetMapping
//    public List<RespondedDto> getAll(){
//        return respondedApplicantsService.getAll();
//    }
//
//    @GetMapping("vacancy/{vacancyId}")
//    public List<UserDto> getByVacancy(@PathVariable int vacancyId){
//        return respondedApplicantsService.getApplicantsByVacancyId(vacancyId);
//    }
//
//    @PostMapping
//    public void respond(@Valid @RequestBody RespondedApplicants responded,
//                        Principal principal) throws UserNotFoundException {
//        if (principal == null) {
//            throw new ForbiddenException();
//        }
//
//        if (responded.getResume() == null || responded.getResume().getId() == null) {
//            throw new IllegalArgumentException("Resume id обязателен");
//        }
//
//        int currentUserId = userService.getUserIdByEmail(principal.getName());
//
//        Resumes resume = resumeRepository.findById(responded.getResume().getId())
//                .orElseThrow(() -> new RuntimeException("Resume not found"));
//
//        if (resume.getApplicant() == null || resume.getApplicant().getId() != currentUserId) {
//            throw new ForbiddenException();
//        }
//
//        respondedApplicantsService.respond(responded);
//    }
//}
