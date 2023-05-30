package com.example.demo.controller.admin;

import org.springframework.security.access.prepost.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.*;

@Controller("adminMainController")
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('admin')")
public class MainController {

	@GetMapping("/main")
    public String mainPage() {
		return "admin/adminMain";
    }
}

