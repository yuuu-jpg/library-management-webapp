package com.library.controller;

import com.library.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loans")
public class LoanController {
    
    @Autowired
    private LoanService loanService;
    
    // 貸出状況一覧画面
    @GetMapping
    public String listLoans(Model model) {
        model.addAttribute("loans", loanService.getCurrentLoans());
        model.addAttribute("loanService", loanService);
        
        return "loans/list";
    }
}
