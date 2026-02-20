package com.library.controller;

import com.library.model.Member;
import com.library.service.LoanService;
import com.library.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/members")
public class MemberController {
    
    @Autowired
    private MemberService memberService;
    
    @Autowired
    private LoanService loanService;
    
    // 会員一覧画面
    @GetMapping
    public String listMembers(Model model) {
        List<Member> members = memberService.getAllMembers();
        Map<Integer, Long> loanCounts = new HashMap<>();
        
        // 会員ごとの貸出回数を計算
        for (Member member : members) {
            long count = loanService.getLoansByMemberId(member.getMemberId()).size();
            loanCounts.put(member.getMemberId(), count);
        }
        
        model.addAttribute("members", members);
        model.addAttribute("loanCounts", loanCounts);
        
        return "members/list";
    }
}
