package com.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    // トップページ
    @GetMapping("/")
    public String home() {
        return "index";
    }
}
