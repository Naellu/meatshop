package com.example.demo.controller.chat;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.*;

@Controller
@Slf4j
@RequestMapping("/chat/")
public class ChatController {
    
    @GetMapping("customer")
    public String chatGET(){

        log.info("@ChatController, chat GET()");
        
        return "chat/customerChat";
    }
}