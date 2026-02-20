package com.library.service;

import com.library.model.Member;
import com.library.repository.LoanRepository;
import com.library.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MemberService {
    
    @Autowired
    private MemberRepository memberRepository;
    
    @Autowired
    private LoanRepository loanRepository;
    
    // すべての会員を取得
    public List<Member> getAllMembers() {
        return memberRepository.findAllMembers();
    }
    
    // 会員をIDで取得
    public Optional<Member> getMemberById(Integer memberId) {
        return memberRepository.findById(memberId);
    }
    
    // 会員ごとの貸出回数を取得
    public Map<Integer, Long> getLoanCountByMember() {
        Map<Integer, Long> loanCounts = new HashMap<>();
        List<Member> members = memberRepository.findAllMembers();
        
        for (Member member : members) {
            long count = loanRepository.findLoansByMemberId(member.getMemberId()).size();
            loanCounts.put(member.getMemberId(), count);
        }
        
        return loanCounts;
    }
}
