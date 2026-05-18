package com.example.job_search.service;

import com.example.job_search.dto.MessageDto;

import java.util.List;

public interface MessageService {
    List<MessageDto> getByRespondedId(int respondedId);
    MessageDto save(int respondedId, String content,  String senderEmail);
}
