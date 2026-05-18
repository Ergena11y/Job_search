package com.example.job_search.controller;

import com.example.job_search.dto.MessageDto;
import com.example.job_search.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ChatWebSocketController {
    private final MessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/{respondedId}")
    public  void sendMessage(@DestinationVariable int respondedId,
                             @Payload String content,
                             Principal principal){
        MessageDto saved = messageService.save(respondedId, content, principal.getName());
        messagingTemplate.convertAndSend("/topic/chat/" + respondedId, saved);
    }
}
