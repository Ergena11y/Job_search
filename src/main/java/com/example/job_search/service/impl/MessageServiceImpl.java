package com.example.job_search.service.impl;

import com.example.job_search.dto.MessageDto;
import com.example.job_search.model.Messages;
import com.example.job_search.model.RespondedApplicants;
import com.example.job_search.model.User;
import com.example.job_search.repository.MessageRepository;
import com.example.job_search.repository.RespondedApplicantsRepository;
import com.example.job_search.repository.UserRepository;
import com.example.job_search.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private  final MessageRepository messageRepository;
    private  final RespondedApplicantsRepository respondedApplicantsRepository;
    private  final UserRepository userRepository;

    @Override
    public List<MessageDto> getByRespondedId(int respondedId) {
        return  messageRepository
                .findByRespondedApplicantsIdOrderByTimeStampAsc(respondedId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MessageDto save(int respondedId, String content, String senderEmail) {
        RespondedApplicants ra = respondedApplicantsRepository.findById(respondedId)
                .orElseThrow(() -> new RuntimeException("RespondedApplicant not found: " + respondedId));

        User sender = userRepository.findByEmail(senderEmail)
                .orElseThrow(() -> new RuntimeException("User not found: " + senderEmail));

        Messages msg = new Messages();
        msg.setRespondedApplicants(ra);
        msg.setContent(content);
        msg.setTimeStamp(LocalDateTime.now());
        msg.setSenderEmail(sender.getEmail());
        msg.setSenderRole(sender.getAccountType());
        Messages saved = messageRepository.save(msg);
        return toDto(saved);
    }


    private  MessageDto toDto(Messages m) {
        MessageDto dto = MessageDto.builder()
                .id(m.getId())
                .respondedApplicantId(m.getRespondedApplicants().getId())
                .content(m.getContent())
                .timeStamp(m.getTimeStamp())
                .senderRole(m.getSenderRole())
                .build();

        if (m.getSenderEmail() != null){
            userRepository.findByEmail(m.getSenderEmail()).ifPresent(user ->
                    dto.setSenderName(user.getName() + " " + user.getSurname()));
        }

        return dto;
    }
}
