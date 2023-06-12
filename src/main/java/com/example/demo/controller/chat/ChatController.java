package com.example.demo.controller.chat;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.*;

@Controller
@Slf4j
public class ChatController {
    
    @GetMapping("/chat")
    public String chatGET(){

        log.info("@ChatController, chat GET()");
        
        return "chat";
    }
}